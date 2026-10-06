/*
 * WaiterService.java 2026-04-12
 *
 * Copyright 2026 Carles Conesa Mañosa, Uriel Neves Silva, Kadiatou Diallo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package model.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jdbc.JdbcDishRepository;
import jdbc.JdbcMenusRepository;
import jdbc.JdbcOrdersRepository;
import jdbc.JdbcReservationsRepository;
import model.dish.Dish;
import model.enums.Allergen;
import model.enums.OrderStatus;
import model.enums.ReservationStatus;
import model.menu.Menu;
import model.order.Order;
import model.order.OrderItem;
import model.table.Reservation;
import model.table.Shift;
import model.table.Table;

/**
 * /src/model/service/WaiterService.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 *
 *         WaiterService centralizes waiter operations: creating and updating
 *         orders, marking orders as paid, table reassignment for pending
 *         reservations, and menu management support used from waiter flows.
 */
public class WaiterService {

    /**
     * Result states for order operations performed by waiter flows.
     */
    public enum OrderResult {
        SUCCESS,
        INVALID_INPUT,
        TABLE_NOT_FOUND,
        RESERVATION_NOT_FOUND,
        RESERVATION_NOT_ACTIVE,
        EMPTY_ORDER,
        DUPLICATE_ORDER,
        RESERVATION_ALREADY_HAS_ORDER,
        ORDER_NOT_FOUND,
        NOT_MODIFIABLE,
        ALREADY_PAID,
        PERSISTENCE_ERROR
    }

    /**
     * Result states for creating dishes from waiter flows.
     */
    public enum DishResult {
        SUCCESS,
        INVALID_DISH,
        DUPLICATE_DISH
    }

    /**
     * Result states for reservation table reassignment.
     */
    public enum TableReassignmentResult {
        SUCCESS,
        INVALID_INPUT,
        RESERVATION_NOT_FOUND,
        RESERVATION_NOT_MODIFIABLE,
        DATE_NOT_TODAY,
        TABLE_NOT_FOUND,
        NO_CAPACITY,
        SHIFT_CONFLICT
    }

    /**
     * Result states for menu creation and deletion.
     */
    public enum MenuResult {
        SUCCESS,
        INVALID_MENU,
        DUPLICATE_MENU,
        MENU_NOT_FOUND
    }

    private List<Order> orders;
    private List<Table> tables;
    private List<Dish> dishes;
    private List<Menu> menus;
    private List<Reservation> reservations;
    private ReservationManager rm;
    private JdbcOrdersRepository orderRepo = new JdbcOrdersRepository();
    private JdbcDishRepository dishRepo = new JdbcDishRepository();
    private JdbcMenusRepository menuRepo = new JdbcMenusRepository();
    private JdbcReservationsRepository resRepo = new JdbcReservationsRepository();
    private LdapUserRepositoryProduction ldapRepo = new LdapUserRepositoryProduction();

    /**
     * Creates the waiter service and loads current data snapshots.
     *
     * @param rm Reservation manager used for shared reservation state.
     */
    public WaiterService(ReservationManager rm) {
        this.rm = rm;
        this.tables = rm.getTables();
        this.reservations = rm.getReservations();
        this.dishes = dishRepo.findAll();
        this.menus = menuRepo.loadAll(dishes);
        this.orders = orderRepo.loadAll(tables, dishes, menus);
    }

    /**
     * Validates whether an order can be started for a table before collecting items.
     *
     * @param tableId Table identifier.
     * @return Descriptive result of the pre-check.
     */
    public OrderResult validateOrderCreationForTable(String tableId) {
        if (tableId == null || tableId.isBlank()) {
            return OrderResult.INVALID_INPUT;
        }

        Table table = null;
        for (Table t : tables) {
            if (t.getId().equals(tableId)) {
                table = t;
                break;
            }
        }
        if (table == null) {
            return OrderResult.TABLE_NOT_FOUND;
        }

        refreshReservationsFromPersistence();
        orders = orderRepo.loadAll(tables, dishes, menus);

        LocalDate today = LocalDate.now();
        Reservation activeReservation = null;

        for (Reservation r : reservations) {
            boolean sameTable = r.getTable().equals(table);
            boolean validStatus = r.getStatus() == ReservationStatus.PENDING
                    || r.getStatus() == ReservationStatus.ACTIVE;
            boolean sameDay = r.getDate().equals(today);

            if (sameTable && sameDay && validStatus) {
                activeReservation = r;
                break;
            }
        }

        if (activeReservation == null) {
            return OrderResult.RESERVATION_NOT_FOUND;
        }

        for (Order o : orders) {
            if (activeReservation.getId().equals(o.getReservationId())) {
                return OrderResult.RESERVATION_ALREADY_HAS_ORDER;
            }
        }

        return OrderResult.SUCCESS;
    }

    /**
     * Adds a new order for today's reservation on the selected table.
     *
     * @param order Order to add.
     * @return Descriptive result of the operation.
     */
    public OrderResult addOrder(Order order) {
        if (order == null || order.getTable() == null) {
            return OrderResult.INVALID_INPUT;
        }

        boolean tableExists = false;
        for (Table t : tables) {
            if (t.getId().equals(order.getTable().getId())) {
                tableExists = true;
                break;
            }
        }
        if (!tableExists) {
            return OrderResult.TABLE_NOT_FOUND;
        }

        LocalDate today = LocalDate.now();
        Reservation activeReservation = null;

        for (Reservation r : reservations) {
            boolean sameTable = r.getTable().equals(order.getTable());
            boolean validStatus = r.getStatus() == ReservationStatus.PENDING
                    || r.getStatus() == ReservationStatus.ACTIVE;
            boolean sameDay = r.getDate().equals(today);

            if (sameTable && sameDay && validStatus) {
                activeReservation = r;
                break;
            }
        }

        if (activeReservation == null) {
            return OrderResult.RESERVATION_NOT_FOUND;
        }

        if (order.getItems().isEmpty()) {
            return OrderResult.EMPTY_ORDER;
        }

        for (Order o : orders) {
            if (o.getId().equals(order.getId())) {
                return OrderResult.DUPLICATE_ORDER;
            }
        }

        for (Order o : orders) {
            if (activeReservation.getId().equals(o.getReservationId())) {
                return OrderResult.RESERVATION_ALREADY_HAS_ORDER;
            }
        }

        order.setReservationId(activeReservation.getId());
        orders.add(order);
        orderRepo.saveAll(orders);

        if (activeReservation.getStatus() == ReservationStatus.PENDING) {
            activeReservation.confirm();
            boolean persisted = resRepo.updateReservationStatus(activeReservation.getId(), ReservationStatus.ACTIVE);
            if (!persisted) {
                return OrderResult.PERSISTENCE_ERROR;
            }
            rm.saveReservations();
        }
        return OrderResult.SUCCESS;
    }

    /**
     * Lists current tables.
     *
     * @return Defensive copy of tables.
     */
    public List<Table> listTables() {
        return new ArrayList<>(tables);
    }

    /**
     * Lists all tables with reservation and allergy context for a date/shift.
     *
     * @param date  Requested date.
     * @param shift Requested shift.
     * @return Formatted context lines for each table.
     */
    public List<String> listTablesWithContext(LocalDate date, Shift shift) {
        refreshReservationsFromPersistence();
        List<String> result = new ArrayList<>();

        for (Table table : tables) {
            StringBuilder sb = new StringBuilder();
            sb.append(table);

            Reservation matchedRes = findBestReservationForSlot(table.getId(), date, shift);

            if (matchedRes != null) {
                sb.append("\n    Reservation : ").append(matchedRes);
            } else {
                sb.append("\n    No reservation for this shift");
            }

            result.add(sb.toString());
        }

        return result;
    }

    /**
     * Lists current orders.
     *
     * @return Defensive copy of orders.
     */
    public List<Order> listOrders() {
        return new ArrayList<>(orders);
    }

    /**
     * Lists available dishes.
     *
     * @return Defensive copy of dishes.
     */
    public List<Dish> listDishes() {
        return new ArrayList<>(dishes);
    }

    /**
     * Lists dishes safe for the customer currently associated with a table.
     *
     * @param tableId Table identifier.
     * @return Safe dishes for the reservation customer, or all dishes when none.
     */
    public List<Dish> listSafeDishesForTable(String tableId) {
        refreshReservationsFromPersistence();
        if (tableId == null || tableId.isBlank()) {
            return new ArrayList<>();
        }

        LocalDate today = LocalDate.now();
        Reservation targetReservation = null;

        for (Reservation r : reservations) {
            boolean sameTable = r.getTable().getId().equals(tableId);
            boolean sameDay = r.getDate().equals(today);
            boolean validStatus = r.getStatus() == ReservationStatus.PENDING
                    || r.getStatus() == ReservationStatus.ACTIVE;

            if (sameTable && sameDay && validStatus) {
                targetReservation = r;
                break;
            }
        }

        if (targetReservation == null || targetReservation.getCustomer() == null) {
            return listDishes();
        }

        String customerId = targetReservation.getCustomer().getId();
        Set<Allergen> allergens = ldapRepo.findCustomerAllergensByUid(customerId);
        return dishRepo.findSafeForAllergens(allergens);
    }

    /**
     * Marks an existing order as paid and completes its reservation in memory.
     *
     * @param orderId Order identifier.
     * @return Descriptive result of the operation.
     */
    public OrderResult markPaid(String orderId) {
        refreshReservationsFromPersistence();
        if (orderId == null || orderId.isBlank()) {
            return OrderResult.INVALID_INPUT;
        }

        for (Order o : orders) {
            if (o.getId().equals(orderId)) {
                boolean reservationActive = false;
                for (Reservation r : reservations) {
                    if (r.getId().equals(o.getReservationId())
                            && r.getStatus() == ReservationStatus.ACTIVE) {
                        reservationActive = true;
                        break;
                    }
                }
                if (!reservationActive) {
                    return OrderResult.RESERVATION_NOT_ACTIVE;
                }

                if (!o.isModifiable()) {
                    if (o.getStatus() == OrderStatus.PAID) {
                        return OrderResult.ALREADY_PAID;
                    }
                    return OrderResult.NOT_MODIFIABLE;
                }

                try {
                    o.markPaid();
                    boolean orderPersisted = orderRepo.updateOrderStatus(o.getId(), o.getStatus());
                    if (!orderPersisted) {
                        return OrderResult.PERSISTENCE_ERROR;
                    }

                    refreshReservationsFromPersistence();

                    return OrderResult.SUCCESS;
                } catch (IllegalStateException e) {
                    return OrderResult.NOT_MODIFIABLE;
                }
            }
        }
        return OrderResult.ORDER_NOT_FOUND;
    }

    /**
     * Adds a new dish when validation passes.
     *
     * @param dish Dish to add.
     * @return Descriptive result of the operation.
     */
    public DishResult addDish(Dish dish) {
        if (dish == null
                || dish.getName() == null
                || dish.getName().isBlank()
                || dish.getPrice() < 0
                || dish.getCategory() == null) {
            return DishResult.INVALID_DISH;
        }

        for (Dish d : dishes) {
            if (d.getName().equalsIgnoreCase(dish.getName())) {
                return DishResult.DUPLICATE_DISH;
            }
        }

        dishes.add(dish);
        dishRepo.saveAll(dishes);
        return DishResult.SUCCESS;
    }

    /**
     * Reloads reservations from persistence to keep waiter views in sync with DB updates.
     */
    private void refreshReservationsFromPersistence() {
        List<Reservation> latest = resRepo.loadAll(tables);
        reservations.clear();
        reservations.addAll(latest);
    }

    /**
     * Picks the most relevant reservation found in DB for a table/date/shift slot.
     */
    private Reservation findBestReservationForSlot(String tableId, LocalDate date, Shift shift) {
        Reservation best = null;

        for (Reservation r : reservations) {
            boolean sameTable = r.getTable().getId().equals(tableId);
            boolean sameDate = r.getDate().equals(date);
            boolean sameShift = r.getShift().getId().equals(shift.getId());

            if (!sameTable || !sameDate || !sameShift) {
                continue;
            }

            if (best == null || reservationStatusPriority(r) > reservationStatusPriority(best)) {
                best = r;
            }
        }

        return best;
    }

    private int reservationStatusPriority(Reservation reservation) {
        return switch (reservation.getStatus()) {
            case ACTIVE -> 6;
            case PENDING -> 5;
            case COMPLETED -> 4;
            case FRAUDULENT -> 3;
            case CANCELLED -> 2;
            case EXPIRED -> 1;
        };
    }

    /**
     * Reassigns a pending reservation to another table for today.
     *
     * @param reservationId Reservation identifier.
     * @param newTableId    New target table identifier.
     * @return Descriptive result of the operation.
     */
    public TableReassignmentResult reassignTable(String reservationId, String newTableId) {
        if (reservationId == null || reservationId.isBlank() || newTableId == null || newTableId.isBlank()) {
            return TableReassignmentResult.INVALID_INPUT;
        }

        Reservation reservation = null;
        for (Reservation r : reservations) {
            if (r.getId().equals(reservationId)) {
                reservation = r;
                break;
            }
        }

        if (reservation == null) {
            return TableReassignmentResult.RESERVATION_NOT_FOUND;
        }

        if (reservation.getStatus() == ReservationStatus.CANCELLED
                || reservation.getStatus() == ReservationStatus.ACTIVE
                || reservation.getStatus() == ReservationStatus.COMPLETED
                || reservation.getStatus() == ReservationStatus.EXPIRED) {
            return TableReassignmentResult.RESERVATION_NOT_MODIFIABLE;
        }

        if (!reservation.getDate().equals(LocalDate.now())) {
            return TableReassignmentResult.DATE_NOT_TODAY;
        }

        Table newTable = null;
        for (Table t : tables) {
            if (t.getId().equals(newTableId)) {
                newTable = t;
                break;
            }
        }

        if (newTable == null) {
            return TableReassignmentResult.TABLE_NOT_FOUND;
        }

        if (!newTable.canAccommodate(reservation.getNumSeats())) {
            return TableReassignmentResult.NO_CAPACITY;
        }

        for (Reservation r : reservations) {
            if (r.getTable().equals(newTable)
                    && r.getDate().equals(reservation.getDate())
                    && r.getStatus() != ReservationStatus.CANCELLED
                    && r.getStatus() != ReservationStatus.EXPIRED
                    && r.getShift().overlaps(reservation.getShift())) {
                return TableReassignmentResult.SHIFT_CONFLICT;
            }
        }

        reservation.getTable().removeBookedShift(reservation.getShift());
        reservation.setTable(newTable);
        newTable.bookShift(reservation.getNumSeats(), reservation.getShift());

        rm.saveReservations();
        return TableReassignmentResult.SUCCESS;
    }

    /**
     * Expires pending reservations whose time window already passed.
     */
    public void cancelExpiredReservations() {
        boolean changed = false;
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        for (Reservation r : reservations) {
            if (r.getStatus() != ReservationStatus.PENDING) {
                continue;
            }

            boolean isExpired = false;

            if (r.getDate().isBefore(today)) {
                isExpired = true;
            } else if (r.getDate().equals(today)) {
                if ((r.getShift().getId().equals("LUNCH_1") && now.isAfter(Shift.LUNCH1.getEndTime()))
                        || (r.getShift().getId().equals("LUNCH_2") && now.isAfter(Shift.LUNCH2.getEndTime()))
                        || (r.getShift().getId().equals("DINNER_1") && now.isAfter(Shift.DINNER1.getEndTime()))
                        || (r.getShift().getId().equals("DINNER_2") && now.isAfter(Shift.DINNER2.getEndTime()))) {
                    isExpired = true;
                }
            }

            if (isExpired) {
                r.expire();
                for (Table t : tables) {
                    if (t.getId().equals(r.getTable().getId())) {
                        t.removeBookedShift(r.getShift());
                        break;
                    }
                }
                changed = true;
            }
        }

        if (changed) {
            rm.saveReservations();
        }
    }

    /**
     * Creates a new menu.
     *
     * @param menu Menu to create.
     * @return Descriptive result of the operation.
     */
    public MenuResult createMenu(Menu menu) {
        if (menu == null
                || menu.getName() == null
                || menu.getName().isBlank()
                || menu.getCourses() == null
                || menu.getCourses().isEmpty()
                || menu.getPrice() < 0) {
            return MenuResult.INVALID_MENU;
        }

        for (Menu m : menus) {
            if (m.getName().equals(menu.getName())) {
                return MenuResult.DUPLICATE_MENU;
            }
        }

        menus.add(menu);
        menuRepo.saveAll(menus);
        return MenuResult.SUCCESS;
    }

    /**
     * Reloads menus from persistence.
     *
     * @return Latest menu list.
     */
    public List<Menu> listMenusFromDatabase() {
        this.dishes = dishRepo.findAll();
        this.menus = menuRepo.loadAll(dishes);
        return new ArrayList<>(menus);
    }

    /**
     * Deletes a menu by name.
     *
     * @param menuName Menu name to delete.
     * @return Descriptive result of the operation.
     */
    public MenuResult deleteMenu(String menuName) {
        if (menuName == null || menuName.isBlank()) {
            return MenuResult.INVALID_MENU;
        }

        List<Menu> existingMenus = menuRepo.loadAll(dishes);
        String canonicalName = null;
        for (Menu m : existingMenus) {
            if (m.getName().equalsIgnoreCase(menuName.trim())) {
                canonicalName = m.getName();
                break;
            }
        }

        if (canonicalName == null) {
            return MenuResult.MENU_NOT_FOUND;
        }

        boolean deleted = menuRepo.deleteByName(canonicalName);
        if (!deleted) {
            return MenuResult.MENU_NOT_FOUND;
        }

        this.menus = menuRepo.loadAll(dishes);
        return MenuResult.SUCCESS;
    }

    /**
     * Lists menus available to waiter workflows.
     *
     * @return Latest menu list.
     */
    public List<Menu> listMenus() {
        return listMenusFromDatabase();
    }

    /**
     * Adds an item to an existing order.
     *
     * @param orderId Order identifier.
     * @param item    New order item.
     * @return Descriptive result of the operation.
     */
    public OrderResult modifyOrder(String orderId, OrderItem item) {
        if (orderId == null || orderId.isBlank() || item == null) {
            return OrderResult.INVALID_INPUT;
        }

        for (Order o : orders) {
            if (o.getId().equals(orderId)) {
                if (!o.isModifiable()) {
                    if (o.getStatus() == OrderStatus.PAID) {
                        return OrderResult.ALREADY_PAID;
                    }
                    return OrderResult.NOT_MODIFIABLE;
                }

                o.addItem(item);
                boolean persisted = orderRepo.addItemToOrder(o.getId(), item);
                if (!persisted) {
                    return OrderResult.PERSISTENCE_ERROR;
                }
                return OrderResult.SUCCESS;
            }
        }
        return OrderResult.ORDER_NOT_FOUND;
    }
}
