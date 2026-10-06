package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dish.Dish;
import model.menu.Menu;

/**
 * Repository class for loading and persisting {@link Menu} entities using JDBC.
 */
public class JdbcMenusRepository {

    /**
     * Loads all menus and their associated courses from persistence.
     *
     * @param dishes In-memory dish catalog used to resolve menu courses.
     * @return List of menus currently stored in the database.
     */
    public List<Menu> loadAll(List<Dish> dishes) {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT name, price FROM menus";

        try (Connection conn = JdbcConnectionFactory.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                List<Dish> courses = loadCoursesForMenu(conn, name, dishes);
                menus.add(new Menu(name, price, courses));
            }

        } catch (SQLException e) {
            System.err.println("Error loading menus: " + e.getMessage());
        }

        return menus;
    }

    /**
     * Saves all menus and course associations, skipping records that already exist.
     *
     * @param menus Menus to persist.
     */
    public void saveAll(List<Menu> menus) {
        String insertMenuSql = "INSERT INTO menus (name, price, dishes) VALUES (?, ?, ?) ON CONFLICT (name) DO NOTHING";
        String insertCourseSql = "INSERT INTO menu_courses (menu_name, dish_name) VALUES (?, ?) "
                + "ON CONFLICT (menu_name, dish_name) DO NOTHING";

        try (Connection conn = JdbcConnectionFactory.connect()) {
            conn.setAutoCommit(false);
            try (PreparedStatement menuStmt = conn.prepareStatement(insertMenuSql);
                 PreparedStatement courseStmt = conn.prepareStatement(insertCourseSql)) {
                int insertedMenus = 0;
                int skippedMenus = 0;
                int insertedCourses = 0;
                int skippedCourses = 0;

                for (Menu m : menus) {
                    String dishesString = serializeDishes(m.getCourses());
                    menuStmt.setString(1, m.getName());
                    menuStmt.setDouble(2, m.getPrice());
                    menuStmt.setString(3, dishesString);
                    int menuRows = menuStmt.executeUpdate();
                    if (menuRows == 1) {
                        insertedMenus++;
                    } else {
                        skippedMenus++;
                    }

                    for (Dish d : m.getCourses()) {
                        courseStmt.setString(1, m.getName());
                        courseStmt.setString(2, d.getName());
                        int courseRows = courseStmt.executeUpdate();
                        if (courseRows == 1) {
                            insertedCourses++;
                        } else {
                            skippedCourses++;
                        }
                    }
                }

                conn.commit();
                System.out.println("   ✓ Inserted " + insertedMenus + " new menus");
                System.out.println("   ✓ Skipped " + skippedMenus + " existing menus");
                System.out.println("   ✓ Inserted " + insertedCourses + " new menu courses");
                System.out.println("   ✓ Skipped " + skippedCourses + " existing menu courses");
                System.out.println("   ✓ Transaction committed");
            } catch (SQLException e) {
                try {
                    conn.rollback();
                    System.out.println("   ⚠️ Transaction rolled back");
                } catch (SQLException rollbackEx) {
                    System.err.println("   ✗ Rollback failed: " + rollbackEx.getMessage());
                }
                System.err.println("✗ Error saving menus: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("✗ Error connecting: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Deletes a menu and all of its course links by menu name.
     *
     * @param menuName Menu name to delete.
     * @return {@code true} if the menu row existed and was deleted.
     */
    public boolean deleteByName(String menuName) {
        String deleteCoursesSql = "DELETE FROM menu_courses WHERE menu_name = ?";
        String deleteMenuSql = "DELETE FROM menus WHERE name = ?";

        try (Connection conn = JdbcConnectionFactory.connect()) {
            conn.setAutoCommit(false);
            try (PreparedStatement deleteCoursesStmt = conn.prepareStatement(deleteCoursesSql);
                 PreparedStatement deleteMenuStmt = conn.prepareStatement(deleteMenuSql)) {
                deleteCoursesStmt.setString(1, menuName);
                deleteCoursesStmt.executeUpdate();

                deleteMenuStmt.setString(1, menuName);
                int deletedRows = deleteMenuStmt.executeUpdate();

                conn.commit();
                return deletedRows == 1;
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed while deleting menu: " + rollbackEx.getMessage());
                }
                System.err.println("Error deleting menu: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error connecting while deleting menu: " + e.getMessage());
        }

        return false;
    }

    private List<Dish> loadCoursesForMenu(Connection conn, String menuName, List<Dish> dishes) {
        List<Dish> courses = new ArrayList<>();
        String sql = "SELECT dish_name FROM menu_courses WHERE menu_name = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, menuName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String dishName = rs.getString("dish_name");
                for (Dish d : dishes) {
                    if (d.getName().equals(dishName)) {
                        courses.add(d);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error loading courses for menu " + menuName + ": " + e.getMessage());
        }

        return courses;
    }

    private String serializeDishes(List<Dish> dishes) {
        if (dishes == null || dishes.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dishes.size(); i++) {
            sb.append(dishes.get(i).getName());
            if (i < dishes.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}