/*
 * ManagerConsole.java 2026-04-12
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

package ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.dish.Dish;
import model.enums.Category;
import model.menu.Menu;
import model.order.Order;
import model.order.OrderItem;
import model.service.ReservationManager;
import model.service.WaiterService;
import model.table.Shift;
import model.table.Table;
import model.user.User;

/**
 * Console workflow for waiter operations: table context, order lifecycle,
 * payment handling, and menu creation/deletion.
 */
public class WaiterConsole {
    private static final Scanner sc = new Scanner(System.in);
    private static WaiterService ws;

    /**
     * Runs the waiter menu loop for the authenticated waiter.
     *
     * @param user Logged-in waiter user.
     * @param rm   Shared reservation manager.
     */
    public static void run(User user, ReservationManager rm) {
        ws = new WaiterService(rm);
        ws.cancelExpiredReservations();

        boolean exit = false;

        String displayName = (user.getName() != null && !user.getName().isBlank()) ? user.getName() : user.getId();
        ui.ConsoleUi.drawHeader("Welcome, " + displayName);

        while (!exit) {
            displayWaiterMenu();
            int choice = getUserChoice();
            executeAction(choice);
            exit = shouldExit(choice);
        }
    }

    /**
     * Displays the main menu to the user.
     */
    private static void displayWaiterMenu() {
        ui.ConsoleUi.drawBox("Waiter Menu", java.util.List.of(
            "1. Manage tables",
            "2. List tables",
            "3. List dishes",
            "4. List table orders",
            "5. Create order",
            "6. Modify order",
            "7. Pay orders",
            "8. Create menu",
            "9. Delete menu",
            "0. Exit"
        ));
        System.out.println();
    }

    /**
     * Gets the user's choice from the console.
     *
     * @param scanner the scanner object to read user input
     * @return the user's choice as an integer
     */
    private static int getUserChoice() {
        int choice = -1;
        while (choice < 0 || choice > 9) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                System.out.println();
            } catch (InputMismatchException e) {
                ui.ConsoleUi.printError("Invalid input. Please enter a number between 0 and 9.");
                sc.nextLine(); // discard the invalid input
                displayWaiterMenu(); // redisplay the menu
            }
            // Try again if choice is invalid
            if (choice < 0 || choice > 9) {
                ui.ConsoleUi.printError("Invalid choice. Please try again.");
                displayWaiterMenu();
            }
            sc.nextLine();
        }
        return choice;
    }

    /**
     * Executes the corresponding action based on the manager's choice.
     *
     * @param choice the manager's choice
     */
    private static void executeAction(int choice) {

        switch (choice) {
            case 0:
                ui.ConsoleUi.printInfo("Logging out...");
                break;
            case 1:
                handleManageTables();
                break;
            case 2:
                handleListTables();
                break;
            case 3:
                handleListDishes();
                break;
            case 4:
                handleListOrders();
                break;
            case 5:
                handleAddOrder();
                break;
            case 6:
                handleModifyOrder();
                break;
            case 7:
                handleMarkPaid();
                break;
            case 8:
                handleCreateMenu();
                break;
            case 9:
                handleDeleteMenu();
                break;
            default:
                ui.ConsoleUi.printError("Invalid choice. Please try again.");
                displayWaiterMenu();
        }
    }

    /**
     * Checks if the user has chosen to exit the program.
     *
     * @param choice the user's choice
     * @return true if the user has chosen to exit, false otherwise
     */
    private static boolean shouldExit(int choice) {
        return choice == 0;
    }

    private static void handleListTables() {
        displayDailyTableOverview();
        Object[] ctx = promptShiftOverride();
        LocalDate date = (LocalDate) ctx[0];
        Shift shift = (Shift) ctx[1];
        List<String> tables = ws.listTablesWithContext(date, shift);
        if (tables.isEmpty()) {
            ui.ConsoleUi.printInfo("No tables found.");
        } else {
            tables.forEach(System.out::println);
        }
    }

    private static void handleMarkPaid() {
        ui.ConsoleUi.drawHeader("Current orders");
        ws.listOrders().forEach(System.out::println);

        System.out.print("\nEnter the order ID: ");
        String orderId = sc.next();

        WaiterService.OrderResult result = ws.markPaid(orderId);

        switch (result) {
            case SUCCESS:
                ui.ConsoleUi.printSuccess("Order marked as paid successfully.");
                break;
            case INVALID_INPUT:
                ui.ConsoleUi.printError("Order ID cannot be empty.");
                break;
            case ORDER_NOT_FOUND:
                ui.ConsoleUi.printError("Order not found. Please check the order ID.");
                break;
            case RESERVATION_NOT_ACTIVE:
                ui.ConsoleUi.printError("The reservation linked to this order is not active.");
                break;
            case ALREADY_PAID:
                ui.ConsoleUi.printError("This order is already marked as paid.");
                break;
            case NOT_MODIFIABLE:
                ui.ConsoleUi.printError("This order cannot be modified in its current status.");
                break;
            case PERSISTENCE_ERROR:
                ui.ConsoleUi.printError("Payment status could not be saved due to a persistence error.");
                break;
            default:
                ui.ConsoleUi.printError("Unable to mark the order as paid.");
                break;
        }
    }

    private static void handleListOrders() {
        List<Order> orders = ws.listOrders();
        if (orders.isEmpty()) {
            ui.ConsoleUi.printInfo("No orders found.");
        } else {
            ui.ConsoleUi.drawHeader("Current Orders");
            orders.forEach(System.out::println);
        }
    }

    private static void handleAddOrder() {
        displayDailyTableOverview();
        Object[] ctx = promptShiftOverride();
        LocalDate date = (LocalDate) ctx[0];
        Shift shift = (Shift) ctx[1];
        ws.listTablesWithContext(date, shift).forEach(System.out::println);

        System.out.print("\nEnter the table ID: ");
        String tableId = sc.next();

        Table table = null;
        for (Table t : ws.listTables()) {
            if (t.getId().equals(tableId)) {
                table = t;
                break;
            }
        }

        if (table == null) {
            ui.ConsoleUi.printError("Table not found.");
            return;
        }

        WaiterService.OrderResult precheck = ws.validateOrderCreationForTable(tableId);
        switch (precheck) {
            case SUCCESS:
                break;
            case INVALID_INPUT:
                ui.ConsoleUi.printError("Table ID cannot be empty.");
                return;
            case TABLE_NOT_FOUND:
                ui.ConsoleUi.printError("Table not found.");
                return;
            case RESERVATION_NOT_FOUND:
                ui.ConsoleUi.printError("No reservation found for this table and current date.");
                return;
            case RESERVATION_ALREADY_HAS_ORDER:
                ui.ConsoleUi.printError("This reservation already has an associated order.");
                return;
            default:
                ui.ConsoleUi.printError("Unable to start order creation for this table.");
                return;
        }

        List<OrderItem> items = new ArrayList<>();
        boolean addingItems = true;

        sc.nextLine();

        while (addingItems) {
            System.out.println("\nWhat do you want to add?");
            System.out.println("  0. Finish");
            System.out.println("  1. Dish");
            System.out.println("  2. Menu");
            System.out.print("\nChoice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "0" -> addingItems = false;
                case "1" -> {
                    List<Dish> dishesToShow = chooseDishListForTable(tableId);
                    if (dishesToShow.isEmpty()) {
                        ui.ConsoleUi.printInfo("No dishes available.");
                        continue;
                    }

                    ui.ConsoleUi.drawHeader("Available dishes");
                    dishesToShow.forEach(System.out::println);
                    System.out.print("\nEnter dish name: ");
                    String dishName = sc.nextLine().trim();

                    Dish dish = null;
                    for (Dish d : dishesToShow) {
                        if (d.getName().equalsIgnoreCase(dishName)) {
                            dish = d;
                            break;
                        }
                    }
                    if (dish == null) {
                        ui.ConsoleUi.printError("Dish not found.");
                        continue;
                    }

                    if (!confirmAllergyRiskIfNeeded(tableId, dish)) {
                        ui.ConsoleUi.printInfo("Dish skipped.");
                        continue;
                    }

                    System.out.print("\nEnter quantity: ");
                    int quantity = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("\nEnter supplement (or press Enter to skip): ");
                    String supplement = sc.nextLine().trim();

                    items.add(new OrderItem(dish, quantity, supplement));
                    ui.ConsoleUi.printSuccess("Dish added.");
                }
                case "2" -> {
                    ui.ConsoleUi.drawHeader("Available menus");
                    ws.listMenus().forEach(System.out::println);
                    System.out.print("Enter menu ID: ");
                    String menuId = sc.nextLine().trim();

                    Menu menu = null;
                    for (Menu m : ws.listMenus()) {
                        if (m.getName().equals(menuId)) {
                            menu = m;
                            break;
                        }
                    }
                    if (menu == null) {
                        ui.ConsoleUi.printError("Menu not found.");
                        continue;
                    }

                    List<Dish> dishPool = chooseDishListForTable(tableId);

                    Map<Category, List<Dish>> byCategory = new HashMap<>();
                    for (Dish d : menu.getCourses()) {
                        byCategory.computeIfAbsent(d.getCategory(), k -> new ArrayList<>()).add(d);
                    }

                    List<Dish> chosen = new ArrayList<>();
                    boolean menuCancelled = false;
                    for (Map.Entry<Category, List<Dish>> entry : byCategory.entrySet()) {
                        List<Dish> filtered = new ArrayList<>();
                        for (Dish d : entry.getValue()) {
                            for (Dish safe : dishPool) {
                                if (safe.getName().equalsIgnoreCase(d.getName())) {
                                    filtered.add(d);
                                    break;
                                }
                            }
                        }
                        if (filtered.isEmpty()) {
                            ui.ConsoleUi.printInfo("No safe options in " + entry.getKey().getDisplayName() + ". Showing all.");
                            filtered = entry.getValue();
                        }

                        System.out.println("\n" + entry.getKey().getDisplayName() + " options:");
                        filtered.forEach(d -> System.out.println("  " + d.getName()));

                        Dish selected = null;
                        while (selected == null) {
                            System.out.print("Choose one: ");
                            String name = sc.nextLine().trim();
                            for (Dish d : filtered) {
                                if (d.getName().equalsIgnoreCase(name)) {
                                    selected = d;
                                    break;
                                }
                            }
                            if (selected == null)
                                ui.ConsoleUi.printError("Invalid choice, try again.");
                        }

                        if (!confirmAllergyRiskIfNeeded(tableId, selected)) {
                            ui.ConsoleUi.printInfo("Menu selection cancelled.");
                            menuCancelled = true;
                            break;
                        }

                        chosen.add(selected);
                    }

                    if (menuCancelled) continue;

                    System.out.print("\nEnter supplement (or press Enter to skip): ");
                    String supplement = sc.nextLine().trim();

                    items.add(new OrderItem(menu, chosen, 1, supplement));
                    ui.ConsoleUi.printSuccess("Menu added.");
                }
                default -> ui.ConsoleUi.printError("Invalid choice.");
            }
        }

        if (items.isEmpty()) {
            ui.ConsoleUi.printInfo("No items added. Order cancelled.");
            return;
        }

        Order order = new Order(null, table, items);

        WaiterService.OrderResult result = ws.addOrder(order);

        switch (result) {
            case SUCCESS:
                ui.ConsoleUi.printSuccess("Order added successfully.");
                break;
            case INVALID_INPUT:
                ui.ConsoleUi.printError("Order data is invalid. Please review the selected table and items.");
                break;
            case TABLE_NOT_FOUND:
                ui.ConsoleUi.printError("Table not found.");
                break;
            case RESERVATION_NOT_FOUND:
                ui.ConsoleUi.printError("No reservation found for this table, date, and shift.");
                break;
            case EMPTY_ORDER:
                ui.ConsoleUi.printError("Cannot create an empty order.");
                break;
            case DUPLICATE_ORDER:
                ui.ConsoleUi.printError("An order with the same ID already exists.");
                break;
            case RESERVATION_ALREADY_HAS_ORDER:
                ui.ConsoleUi.printError("This reservation already has an associated order.");
                break;
            case PERSISTENCE_ERROR:
                ui.ConsoleUi.printError("Order could not be saved due to a persistence error.");
                break;
            default:
                ui.ConsoleUi.printError("Unable to create the order.");
                break;
        }
    }

    private static void handleModifyOrder() {
        ui.ConsoleUi.drawHeader("Current orders");
        ws.listOrders().forEach(System.out::println);

        System.out.print("\nEnter the order ID to modify: ");
        String orderId = sc.nextLine().trim();
        Order selectedOrder = findOrderById(orderId);
        if (selectedOrder == null) {
            ui.ConsoleUi.printError("Order not found.");
            return;
        }

        if (!selectedOrder.isModifiable()) {
            if ("PAID".equals(selectedOrder.getStatus().name())) {
                ui.ConsoleUi.printError("This order is already paid and cannot be modified.");
            } else {
                ui.ConsoleUi.printError("This order cannot be modified in its current status.");
            }
            return;
        }

        String tableId = selectedOrder.getTable().getId();

        boolean addingItems = true;
        while (addingItems) {
            System.out.println("\nWhat do you want to add?");
            System.out.println("  0. Finish");
            System.out.println("  1. Dish");
            System.out.println("  2. Menu");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "0" -> addingItems = false;
                case "1" -> {
                    List<Dish> dishesToShow = chooseDishListForTable(tableId);
                    if (dishesToShow.isEmpty()) {
                        ui.ConsoleUi.printInfo("No dishes available.");
                        continue;
                    }

                    ui.ConsoleUi.drawHeader("Available dishes");
                    dishesToShow.forEach(System.out::println);
                    System.out.print("Enter dish name: ");
                    String dishName = sc.nextLine().trim();

                    Dish dish = null;
                    for (Dish d : dishesToShow) {
                        if (d.getName().equalsIgnoreCase(dishName)) {
                            dish = d;
                            break;
                        }
                    }
                    if (dish == null) {
                        ui.ConsoleUi.printError("Dish not found.");
                        continue;
                    }

                    if (!confirmAllergyRiskIfNeeded(tableId, dish)) {
                        ui.ConsoleUi.printInfo("Dish skipped.");
                        continue;
                    }

                    System.out.print("Enter quantity: ");
                    int quantity = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Enter supplement (or press Enter to skip): ");
                    String supplement = sc.nextLine().trim();

                    WaiterService.OrderResult result = ws.modifyOrder(orderId,
                            new OrderItem(dish, quantity, supplement));
                    handleModifyOrderResult(result, "Dish added.");
                }
                case "2" -> {
                    ui.ConsoleUi.drawHeader("Available menus");
                    ws.listMenus().forEach(System.out::println);
                    System.out.print("Enter menu name: ");
                    String menuName = sc.nextLine().trim();

                    Menu menu = null;
                    for (Menu m : ws.listMenus()) {
                        if (m.getName().equalsIgnoreCase(menuName)) {
                            menu = m;
                            break;
                        }
                    }
                    if (menu == null) {
                        ui.ConsoleUi.printError("Menu not found.");
                        continue;
                    }

                    List<Dish> dishPool = chooseDishListForTable(tableId);

                    Map<Category, List<Dish>> byCategory = new HashMap<>();
                    for (Dish d : menu.getCourses()) {
                        byCategory.computeIfAbsent(d.getCategory(), k -> new ArrayList<>()).add(d);
                    }

                    List<Dish> chosen = new ArrayList<>();
                    boolean menuCancelled = false;
                    for (Map.Entry<Category, List<Dish>> entry : byCategory.entrySet()) {
                        List<Dish> filtered = new ArrayList<>();
                        for (Dish d : entry.getValue()) {
                            for (Dish safe : dishPool) {
                                if (safe.getName().equalsIgnoreCase(d.getName())) {
                                    filtered.add(d);
                                    break;
                                }
                            }
                        }
                        if (filtered.isEmpty()) {
                            ui.ConsoleUi.printInfo("No safe options in " + entry.getKey().getDisplayName() + ". Showing all.");
                            filtered = entry.getValue();
                        }

                        System.out.println("\n" + entry.getKey().getDisplayName() + " options:");
                        filtered.forEach(d -> System.out.println("  " + d.getName()));

                        Dish selected = null;
                        while (selected == null) {
                            System.out.print("Choose one: ");
                            String name = sc.nextLine().trim();
                            for (Dish d : filtered) {
                                if (d.getName().equalsIgnoreCase(name)) {
                                    selected = d;
                                    break;
                                }
                            }
                            if (selected == null)
                                ui.ConsoleUi.printError("Invalid choice, try again.");
                        }

                        if (!confirmAllergyRiskIfNeeded(tableId, selected)) {
                            ui.ConsoleUi.printInfo("Menu selection cancelled.");
                            menuCancelled = true;
                            break;
                        }

                        chosen.add(selected);
                    }

                    if (menuCancelled) continue;

                    System.out.print("Enter supplement (or press Enter to skip): ");
                    String supplement = sc.nextLine().trim();

                    WaiterService.OrderResult result = ws.modifyOrder(orderId,
                            new OrderItem(menu, chosen, 1, supplement));
                    handleModifyOrderResult(result, "Menu added.");
                }
                default -> ui.ConsoleUi.printError("Invalid choice.");
            }
        }
    }

    private static void handleListDishes() {
        List<Dish> dishes = ws.listDishes();

        if (dishes.isEmpty()) {
            ui.ConsoleUi.printInfo("No dishes found.");
        } else {
            ui.ConsoleUi.drawHeader("List of Dishes");
            dishes.forEach(System.out::println);
        }
    }

    private static void handleManageTables() {
        displayDailyTableOverview();
        Object[] ctx = promptShiftOverride();
        LocalDate date = (LocalDate) ctx[0];
        Shift shift = (Shift) ctx[1];
        ws.listTablesWithContext(date, shift).forEach(System.out::println);

        System.out.print("\nEnter the reservation ID to reassign: ");
        String reservationId = sc.next().trim();

        System.out.print("Enter the new table ID: ");
        String newTableId = sc.next().trim();

        WaiterService.TableReassignmentResult result = ws.reassignTable(reservationId, newTableId);

        switch (result) {
            case SUCCESS:
                ui.ConsoleUi.printSuccess("Table reassigned successfully.");
                break;
            case INVALID_INPUT:
                ui.ConsoleUi.printError("Reservation ID and table ID are required.");
                break;
            case RESERVATION_NOT_FOUND:
                ui.ConsoleUi.printError("Reservation not found.");
                break;
            case RESERVATION_NOT_MODIFIABLE:
                ui.ConsoleUi.printError("This reservation can no longer be reassigned.");
                break;
            case DATE_NOT_TODAY:
                ui.ConsoleUi.printError("Only today's reservations can be reassigned.");
                break;
            case TABLE_NOT_FOUND:
                ui.ConsoleUi.printError("Target table not found.");
                break;
            case NO_CAPACITY:
                ui.ConsoleUi.printError("Target table does not have enough capacity.");
                break;
            case SHIFT_CONFLICT:
                ui.ConsoleUi.printError("Target table is already booked for this reservation shift.");
                break;
            default:
                ui.ConsoleUi.printError("Unable to reassign the table.");
                break;
        }
    }

    /**
     * Displays all tables with their availability across all shifts for today.
     * Gives the waiter a complete overview before selecting a specific shift.
     */
    private static void displayDailyTableOverview() {
        LocalDate today = LocalDate.now();
        Shift[] shifts = {Shift.LUNCH1, Shift.LUNCH2, Shift.DINNER1, Shift.DINNER2};
        
        ui.ConsoleUi.drawHeader("Daily Overview - All Tables (" + today + ")");
        
        for (Shift shift : shifts) {
            System.out.println("\n" + shift.getId() + " (" + shift.getStartTime() + " - " + shift.getEndTime() + "):");
            List<String> availableTables = ws.listTablesWithContext(today, shift);
            if (availableTables.isEmpty()) {
                System.out.println("  All tables are booked");
            } else {
                availableTables.forEach(t -> System.out.println("  " + t));
            }
        }
        System.out.println();
    }

    private static void handleCreateMenu() {
        List<Menu> existingMenus = ws.listMenusFromDatabase();
        if (!existingMenus.isEmpty()) {
            ui.ConsoleUi.drawHeader("Existing menus");
            existingMenus.forEach(System.out::println);
        }

        System.out.print("\nEnter the menu name: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) {
            ui.ConsoleUi.printError("The menu name cannot be empty.");
            return;
        }

        System.out.print("Enter menu price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        List<Dish> courses = new ArrayList<>();
        boolean addingCourses = true;

        ui.ConsoleUi.drawHeader("Available dishes");
        ws.listDishes().forEach(System.out::println);

        while (addingCourses) {
            System.out.print("\nEnter dish name to add (or 'done' to finish): ");
            String dishName = sc.nextLine().trim();

            if (dishName.equalsIgnoreCase("done")) {
                addingCourses = false;
                continue;
            }

            Dish dish = null;
            for (Dish d : ws.listDishes()) {
                if (d.getName().equalsIgnoreCase(dishName)) {
                    dish = d;
                    break;
                }
            }

            if (dish == null) {
                ui.ConsoleUi.printError("Dish not found.");
                continue;
            }

            courses.add(dish);
            ui.ConsoleUi.printSuccess("Added: " + dish.getName());
        }

        if (courses.isEmpty()) {
            ui.ConsoleUi.printInfo("No courses added. Menu cancelled.");
            return;
        }

        Menu menu = new Menu(id, price, courses);

        WaiterService.MenuResult result = ws.createMenu(menu);

        switch (result) {
            case SUCCESS:
                ui.ConsoleUi.printSuccess("Menu created successfully.");
                break;
            case INVALID_MENU:
                ui.ConsoleUi.printError("Menu data is invalid. Check name, price, and courses.");
                break;
            case DUPLICATE_MENU:
                ui.ConsoleUi.printError("A menu with this name already exists.");
                break;
            default:
                ui.ConsoleUi.printError("Unable to create menu.");
                break;
        }
    }

    private static void handleDeleteMenu() {
        List<Menu> menus = ws.listMenusFromDatabase();

        if (menus.isEmpty()) {
            ui.ConsoleUi.printInfo("No menus found.");
            return;
        }

        ui.ConsoleUi.drawHeader("Current menus");
        menus.forEach(System.out::println);

        System.out.print("\nEnter menu name to delete: ");
        String menuName = sc.nextLine().trim();

        WaiterService.MenuResult result = ws.deleteMenu(menuName);
        switch (result) {
            case SUCCESS:
                ui.ConsoleUi.printSuccess("Menu deleted successfully.");
                break;
            case INVALID_MENU:
                ui.ConsoleUi.printError("Menu name cannot be empty.");
                break;
            case MENU_NOT_FOUND:
                ui.ConsoleUi.printError("Menu not found. Please check the menu name.");
                break;
            default:
                ui.ConsoleUi.printError("Unable to delete menu.");
                break;
        }
    }

    /**
     * Prints an order-modification outcome using a consistent message mapping.
     *
     * @param result         Outcome from the service layer.
     * @param successMessage Message to print when operation succeeds.
     */
    private static void handleModifyOrderResult(WaiterService.OrderResult result, String successMessage) {
        switch (result) {
            case SUCCESS:
                ui.ConsoleUi.printSuccess(successMessage);
                break;
            case INVALID_INPUT:
                ui.ConsoleUi.printError("Invalid order data.");
                break;
            case ORDER_NOT_FOUND:
                ui.ConsoleUi.printError("Order not found.");
                break;
            case ALREADY_PAID:
                ui.ConsoleUi.printError("This order is already paid and cannot be modified.");
                break;
            case NOT_MODIFIABLE:
                ui.ConsoleUi.printError("This order cannot be modified in its current status.");
                break;
            case PERSISTENCE_ERROR:
                ui.ConsoleUi.printError("The order update could not be saved due to a persistence error.");
                break;
            default:
                ui.ConsoleUi.printError("Unable to modify the order.");
                break;
        }
    }

    private static Order findOrderById(String orderId) {
        for (Order order : ws.listOrders()) {
            if (order.getId().equalsIgnoreCase(orderId)) {
                return order;
            }
        }
        return null;
    }

    private static List<Dish> chooseDishListForTable(String tableId) {
        while (true) {
            System.out.println("\nDish list mode:");
            System.out.println("  1. Show only safe dishes");
            System.out.println("  2. Show all dishes");
            System.out.print("Choice: ");
            String mode = sc.nextLine().trim();

            if (mode.equals("1")) {
                return ws.listSafeDishesForTable(tableId);
            }

            if (mode.equals("2")) {
                return ws.listDishes();
            }

            ui.ConsoleUi.printError("Invalid choice. Please enter 1 or 2.");
        }
    }

    private static boolean confirmAllergyRiskIfNeeded(String tableId, Dish dish) {
        List<Dish> safeDishes = ws.listSafeDishesForTable(tableId);

        for (Dish safeDish : safeDishes) {
            if (safeDish.getName().equalsIgnoreCase(dish.getName())) {
                return true;
            }
        }

        while (true) {
            ui.ConsoleUi.printError("Warning: the table responsible customer may be allergic to this dish.");
            System.out.print("Do you want to continue? (y/n): ");
            String confirm = sc.nextLine().trim().toLowerCase();

            if (confirm.equals("y")) {
                return true;
            }
            if (confirm.equals("n")) {
                return false;
            }

            ui.ConsoleUi.printError("Invalid option. Please type 'y' or 'n'.");
        }
    }

    /** Returns the current or next upcoming shift based on the local clock. */
    private static Shift findDefaultShift() {
        LocalTime now = LocalTime.now();
        if (!now.isAfter(Shift.LUNCH1.getEndTime()))  return Shift.LUNCH1;
        if (!now.isAfter(Shift.LUNCH2.getEndTime()))  return Shift.LUNCH2;
        if (!now.isAfter(Shift.DINNER1.getEndTime())) return Shift.DINNER1;
        if (!now.isAfter(Shift.DINNER2.getEndTime())) return Shift.DINNER2;
        return Shift.LUNCH1; // after 23:00 — default to first shift of next day
    }

    /**
     * Shows the auto-detected shift and lets the waiter override it.
     * Returns an Object[] {LocalDate, Shift}.
     */
    private static Object[] promptShiftOverride() {
        Shift defaultShift = findDefaultShift();
        LocalDate defaultDate = LocalTime.now().isAfter(Shift.DINNER2.getEndTime())
                ? LocalDate.now().plusDays(1)
                : LocalDate.now();

        String dateLabel = defaultDate.equals(LocalDate.now()) ? "today (" + defaultDate + ")" : defaultDate.toString();
        System.out.println();
        ui.ConsoleUi.drawHeader("Tables for: " + defaultShift + " — " + dateLabel);
        System.out.print("Press ENTER to confirm, or type a shift (LUNCH_1 / LUNCH_2 / DINNER_1 / DINNER_2): ");

        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            return new Object[]{defaultDate, defaultShift};
        }

        Shift chosenShift = switch (input.toUpperCase()) {
            case "LUNCH_1"  -> Shift.LUNCH1;
            case "LUNCH_2"  -> Shift.LUNCH2;
            case "DINNER_1" -> Shift.DINNER1;
            case "DINNER_2" -> Shift.DINNER2;
            default -> null;
        };

        if (chosenShift == null) {
            ui.ConsoleUi.printError("Unknown shift '" + input + "'. Using default: " + defaultShift);
            chosenShift = defaultShift;
        }

        System.out.print("Enter date (YYYY-MM-DD) or press ENTER for today: ");
        String dateInput = sc.nextLine().trim();
        LocalDate chosenDate = defaultDate;
        if (!dateInput.isEmpty()) {
            try {
                chosenDate = LocalDate.parse(dateInput);
            } catch (Exception e) {
                ui.ConsoleUi.printError("Invalid date format. Using today.");
            }
        }

        ui.ConsoleUi.drawHeader("Tables for: " + chosenShift + " — " + chosenDate);
        return new Object[]{chosenDate, chosenShift};
    }
}