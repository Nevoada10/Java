package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.dish.Dish;
import model.enums.OrderStatus;
import model.menu.Menu;
import model.order.Order;
import model.order.OrderItem;
import model.table.Table;

/**
 * Repository class for loading and persisting {@link Order} entities using JDBC.
 */
public class JdbcOrdersRepository {

    /**
     * Loads all orders with their items from the database.
     *
     * @param tables In-memory table catalog to resolve table references.
     * @param dishes In-memory dish catalog to resolve order items.
     * @param menus  In-memory menu catalog to resolve order items.
     * @return List of orders currently stored in persistence.
     */
    public List<Order> loadAll(List<Table> tables, List<Dish> dishes, List<Menu> menus) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT id, reservation_id, table_id, status, created_at FROM orders";

        try (Connection conn = JdbcConnectionFactory.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String tableId = rs.getString("table_id");
                Table table = null;
                for (Table t : tables) {
                    if (t.getId().equals(tableId)) {
                        table = t;
                        break;
                    }
                }
                if (table == null)
                    continue;

                String orderId = rs.getString("id");
                String reservationId = rs.getString("reservation_id");
                OrderStatus status = OrderStatus.valueOf(rs.getString("status"));
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

                List<OrderItem> items = loadItemsForOrder(conn, orderId, dishes, menus);
                Order order = new Order(reservationId, orderId, table, items, status, createdAt);
                orders.add(order);
            }

        } catch (SQLException e) {
            System.err.println("Error loading orders: " + e.getMessage());
        }

        return orders;
    }

    /**
     * Persists new orders and their items, skipping already existing order IDs.
     *
     * @param orders Orders to persist.
     */
    public void saveAll(List<Order> orders) {
        String insertOrderSql = "INSERT INTO orders (id, reservation_id, table_id, status, created_at) VALUES (?, ?, ?, ?, ?) "
                + "ON CONFLICT (id) DO NOTHING";
        String insertItemSql = "INSERT INTO order_items (order_id, type, name, price, quantity, supplement) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        String insertChosenCourseSql = "INSERT INTO order_item_chosen_courses (order_item_id, dish_name) VALUES (?, ?)";

        try (Connection conn = JdbcConnectionFactory.connect()) {
            conn.setAutoCommit(false);
            Set<String> existingOrderIds = loadExistingOrderIds(conn);
            try (PreparedStatement orderStmt = conn.prepareStatement(insertOrderSql);
                 PreparedStatement itemStmt = conn.prepareStatement(insertItemSql);
                 PreparedStatement chosenCourseStmt = conn.prepareStatement(insertChosenCourseSql)) {
                int insertedOrders = 0;
                int skippedOrders = 0;
                int insertedItems = 0;

                for (Order o : orders) {
                    if (existingOrderIds.contains(o.getId())) {
                        skippedOrders++;
                        continue;
                    }

                    orderStmt.setString(1, o.getId());
                    orderStmt.setString(2, o.getReservationId());
                    orderStmt.setString(3, o.getTable().getId());
                    orderStmt.setString(4, o.getStatus().name());
                    orderStmt.setTimestamp(5, Timestamp.valueOf(o.getCreatedAt()));
                    int rows = orderStmt.executeUpdate();
                    if (rows == 0) {
                        skippedOrders++;
                        continue;
                    }

                    insertedOrders++;
                    for (OrderItem item : o.getItems()) {
                        itemStmt.setString(1, o.getId());
                        if (item.isDish()) {
                            itemStmt.setString(2, "DISH");
                            itemStmt.setString(3, item.getDish().getName());
                            itemStmt.setDouble(4, item.getDish().getPrice());
                        } else {
                            itemStmt.setString(2, "MENU");
                            itemStmt.setString(3, item.getMenu().getName());
                            itemStmt.setDouble(4, item.getMenu().getPrice());
                        }
                        itemStmt.setInt(5, item.getQuantity());
                        itemStmt.setString(6, item.getSupplement());

                        try (ResultSet generatedId = itemStmt.executeQuery()) {
                            if (generatedId.next()) {
                                int itemId = generatedId.getInt(1);
                                insertedItems++;

                                if (item.isMenu()) {
                                    for (Dish d : item.getChosenCourses()) {
                                        chosenCourseStmt.setInt(1, itemId);
                                        chosenCourseStmt.setString(2, d.getName());
                                        chosenCourseStmt.executeUpdate();
                                    }
                                }
                            }
                        }
                    }
                }

                conn.commit();
                System.out.println("   ✓ Inserted " + insertedOrders + " new orders with " + insertedItems + " items");
                System.out.println("   ✓ Skipped " + skippedOrders + " existing orders");
                System.out.println("   ✓ Transaction committed");
            } catch (SQLException e) {
                try {
                    conn.rollback();
                    System.out.println("   ⚠️ Transaction rolled back");
                } catch (SQLException rollbackEx) {
                    System.err.println("   ✗ Rollback failed: " + rollbackEx.getMessage());
                }
                System.err.println("✗ Error saving orders: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("✗ Error connecting: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Adds one item to an existing order and persists any chosen courses for menu items.
     *
     * @param orderId Target order identifier.
     * @param item    Order item to append.
     * @return {@code true} when the operation commits successfully.
     */
    public boolean addItemToOrder(String orderId, OrderItem item) {
        String insertItemSql = "INSERT INTO order_items (order_id, type, name, price, quantity, supplement) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        String insertChosenCourseSql = "INSERT INTO order_item_chosen_courses (order_item_id, dish_name) VALUES (?, ?)";

        try (Connection conn = JdbcConnectionFactory.connect()) {
            conn.setAutoCommit(false);
            try (PreparedStatement itemStmt = conn.prepareStatement(insertItemSql);
                 PreparedStatement chosenCourseStmt = conn.prepareStatement(insertChosenCourseSql)) {

                itemStmt.setString(1, orderId);
                if (item.isDish()) {
                    itemStmt.setString(2, "DISH");
                    itemStmt.setString(3, item.getDish().getName());
                    itemStmt.setDouble(4, item.getDish().getPrice());
                } else {
                    itemStmt.setString(2, "MENU");
                    itemStmt.setString(3, item.getMenu().getName());
                    itemStmt.setDouble(4, item.getMenu().getPrice());
                }
                itemStmt.setInt(5, item.getQuantity());
                itemStmt.setString(6, item.getSupplement());

                try (ResultSet generatedId = itemStmt.executeQuery()) {
                    if (generatedId.next()) {
                        int itemId = generatedId.getInt(1);
                        if (item.isMenu()) {
                            for (Dish d : item.getChosenCourses()) {
                                chosenCourseStmt.setInt(1, itemId);
                                chosenCourseStmt.setString(2, d.getName());
                                chosenCourseStmt.executeUpdate();
                            }
                        }
                    }
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed while adding item: " + rollbackEx.getMessage());
                }
                System.err.println("Error adding item to order: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error connecting while adding item: " + e.getMessage());
        }
        return false;
    }

    /**
     * Updates an order status in persistence.
     *
     * @param orderId Order identifier.
     * @param status  New status to store.
     * @return {@code true} if at least one row was updated.
     */
    public boolean updateOrderStatus(String orderId, OrderStatus status) {
        String updateSql = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection conn = JdbcConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {
            stmt.setString(1, status.name());
            stmt.setString(2, orderId);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.err.println("No order updated for id: " + orderId);
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating order status: " + e.getMessage());
            return false;
        }
    }

    private Set<String> loadExistingOrderIds(Connection conn) throws SQLException {
        Set<String> orderIds = new HashSet<>();
        String sql = "SELECT id FROM orders";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                orderIds.add(rs.getString("id"));
            }
        }
        return orderIds;
    }

    private List<OrderItem> loadItemsForOrder(Connection conn, String orderId, List<Dish> dishes, List<Menu> menus) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT id, type, name, price, quantity, supplement FROM order_items WHERE order_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int itemId = rs.getInt("id");
                String type = rs.getString("type");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                String supplement = rs.getString("supplement");

                if (type.equals("DISH")) {
                    Dish dish = null;
                    for (Dish d : dishes) {
                        if (d.getName().equals(name)) {
                            dish = d;
                            break;
                        }
                    }
                    if (dish == null)
                        continue;
                    items.add(new OrderItem(dish, quantity, supplement));

                } else if (type.equals("MENU")) {
                    Menu menu = null;
                    for (Menu m : menus) {
                        if (m.getName().equals(name)) {
                            menu = m;
                            break;
                        }
                    }
                    if (menu == null)
                        continue;

                    List<Dish> chosenCourses = loadChosenCourses(conn, itemId, dishes);
                    items.add(new OrderItem(menu, chosenCourses, quantity, supplement));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error loading items for order " + orderId + ": " + e.getMessage());
        }

        return items;
    }

    private List<Dish> loadChosenCourses(Connection conn, int orderItemId, List<Dish> dishes) {
        List<Dish> chosen = new ArrayList<>();
        String sql = "SELECT dish_name FROM order_item_chosen_courses WHERE order_item_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderItemId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String dishName = rs.getString("dish_name");
                for (Dish d : dishes) {
                    if (d.getName().equals(dishName)) {
                        chosen.add(d);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error loading chosen courses for item " + orderItemId + ": " + e.getMessage());
        }

        return chosen;
    }

    /**
     * Calculates daily revenue from all PAID orders created today.
     * Sums order items (price * quantity) where the order status is PAID 
     * and the creation date matches today's date.
     *
     * @return Total revenue from today's paid orders, or 0 if none exist.
     */
    public double getDailyRevenue() {
        String sql = "SELECT COALESCE(SUM(oi.price * oi.quantity), 0) AS daily_revenue "
                   + "FROM order_items oi "
                   + "JOIN orders o ON oi.order_id = o.id "
                   + "WHERE o.status = 'PAID' "
                   + "AND DATE(o.created_at) = CURRENT_DATE";

        try (Connection conn = JdbcConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("daily_revenue");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating daily revenue: " + e.getMessage());
        }

        return 0.0;
    }
}
