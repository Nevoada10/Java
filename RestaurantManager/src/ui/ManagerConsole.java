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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import jdbc.JdbcConnectionFactory;
import model.dish.Dish;
import model.enums.Allergen;
import model.enums.Category;
import model.enums.Role;
import model.service.ReservationManager;
import model.service.WaiterService;
import model.menu.Menu;
import model.order.Order;
import model.table.Reservation;
import model.table.Table;
import model.user.Manager;
import model.user.User;

/**
 * Console workflow for manager operations including user administration,
 * reservations overview, dish maintenance, and daily revenue access.
 */
public class ManagerConsole {
    private static final Scanner sc = new Scanner(System.in);
    private static ReservationManager rm;
    private static WaiterService ws;

    /**
     * Runs the manager menu loop for the authenticated user.
     *
     * @param user Logged-in manager user.
     * @param rm   Shared reservation manager.
     */
    public static void run(User user, ReservationManager rm) {
        ManagerConsole.rm = rm;
        ws = new WaiterService(rm);
        boolean exit = false;

        String displayName = (user.getName() != null && !user.getName().isBlank()) ? user.getName() : user.getId();
        ui.ConsoleUi.drawHeader("Welcome, " + displayName);

        while (!exit) {
            displayManagerMenu();
            int choice = getUserChoice();
            executeAction(choice);
            exit = shouldExit(choice);
        }
    }

    /**
     * Displays the main menu to the user.
     */
    private static void displayManagerMenu() {
        ui.ConsoleUi.drawBox("Manager Menu", java.util.List.of(
            "1. Add user",
            "2. Remove user",
            "3. List users",
            "4. Get daily revenue",
            "5. List reservations",
            "6. Add dish",
            "7. Delete dish",
            "8. Database queries (SELECT)",
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
        while (choice < 0 || choice > 8) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                ui.ConsoleUi.printError("Invalid input. Please enter a number between 0 and 8.");
                sc.nextLine(); // discard the invalid input
                displayManagerMenu(); // redisplay the menu
            }
            // Try again if choice is invalid
            if (choice < 0 || choice > 8) {
                ui.ConsoleUi.printError("Invalid choice. Please try again.");
                displayManagerMenu();
            }

        }
        sc.nextLine();
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
                handleAddUser();
                break;
            case 2:
                handleRemoveUser();
                break;
            case 3:
                handleListUsersGrouped();
                break;
            case 4:
                handleDailyRevenue();
                break;
            case 5:
                handleListReservations();
                break;
            case 6:
                handleAddDish();
                break;
            case 7:
                handleDeleteDish();
                break;
            case 8:
                handleDatabaseQueries();
                break;
            default:
                ui.ConsoleUi.printError("Invalid choice. Please try again.");
                displayManagerMenu();
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

    private static void handleAddUser() {
        ui.ConsoleUi.drawHeader("Create new user");
        System.out.println("Please fill in the following fields:");
        System.out.println("- User ID: unique login (example: marta01)");
        System.out.println("- Full name: display name (example: Marta Garcia)");
        System.out.println("- Password: temporary password for first login");
        System.out.println("- Role: MANAGER, WAITER or CUSTOMER");

        System.out.print("\nType user ID: ");
        String userId = sc.nextLine().trim();

        System.out.print("Type full name: ");
        String name = sc.nextLine().trim();

        System.out.print("Type password: ");
        String password = sc.nextLine().trim();

        System.out.print("Type role (MANAGER/WAITER/CUSTOMER): ");
        String roleInput = sc.nextLine().trim();

        if (userId.isEmpty() || name.isEmpty() || password.isEmpty() || roleInput.isEmpty()) {
            ui.ConsoleUi.printError("All fields are required. User was not created.");
            return;
        }

        try {
            Role role = Role.valueOf(roleInput.toUpperCase());

            ui.ConsoleUi.drawHeader("Confirm new user");
            System.out.println("ID: " + userId);
            System.out.println("Name: " + name);
            System.out.println("Role: " + role.name());

            String confirmation;
            while (true) {
                System.out.print("Create this user? (Y/N): ");
                confirmation = sc.nextLine().trim().toUpperCase();
                if ("Y".equals(confirmation) || "N".equals(confirmation)) {
                    break;
                }
                ui.ConsoleUi.printError("Invalid answer. Please type Y or N.");
            }

            if ("N".equals(confirmation)) {
                ui.ConsoleUi.printInfo("User creation cancelled.");
                return;
            }

            User user = new User(userId, name, password, role);

            boolean addedUser = rm.addUser(user);

            if (addedUser) {
                ui.ConsoleUi.printSuccess("User added successfully.");
            } else {
                ui.ConsoleUi.printError("Could not add user. The ID may already exist or the provided data is invalid.");
            }

        } catch (IllegalArgumentException e) {
            ui.ConsoleUi.printError("Invalid role entered.");
        }
    }

    private static void handleRemoveUser() {
        handleListUsersGrouped();

        System.out.println("Enter user ID: ");
        String userId = sc.nextLine();

        boolean removedUser = rm.removeUser(userId);

        if (removedUser) {
            ui.ConsoleUi.printSuccess("User removed successfully.");
        } else {
            ui.ConsoleUi.printError("Could not remove user. Please verify the user ID exists.");
        }
    }

    private static void handleListUsersGrouped() {
        List<User> users = rm.listUsers();

        if (users.isEmpty()) {
            ui.ConsoleUi.printInfo("No users found.");
            return;
        }

        ui.ConsoleUi.drawHeader("Users grouped by role");
        for (Role role : Role.values()) {
            System.out.println("\n" + role.name() + ":");
            boolean foundUsersForRole = false;

            for (User user : users) {
                if (user.getRole() == role) {
                    System.out.println("  - ID: " + user.getId() + " | Name: " + user.getName());
                    foundUsersForRole = true;
                }
            }

            if (!foundUsersForRole) {
                System.out.println("  (none)");
            }
        }
    }

    private static void handleDailyRevenue() {
        double currentRevenue = rm.getDailyRevenue();
        ui.ConsoleUi.drawHeader(String.format("Current Daily Revenue: $%.2f", currentRevenue));
    }

    private static void handleListReservations() {
        List<Reservation> reservations = rm.listReservations();
        if (reservations.isEmpty()) {
            ui.ConsoleUi.printInfo("No reservations found.");
        } else {
            ui.ConsoleUi.drawHeader("Current Reservations");
            reservations.forEach(System.out::println);
        }
    }

    private static void handleAddDish() {
        System.out.print("\nEnter dish name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            ui.ConsoleUi.printError("Name cannot be empty.");
            return;
        }

        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        // Show available categories so input can match valid enum values.
        ui.ConsoleUi.drawHeader("Available categories");
        for (Category c : Category.values()) {
            System.out.println("  • " + c.name() + " — " + c.getDisplayName());
        }

        Category category = null;
        while (category == null) {
            System.out.print("Enter category: ");
            String categoryInput = sc.nextLine().trim().toUpperCase();
            try {
                category = Category.valueOf(categoryInput);
            } catch (IllegalArgumentException e) {
                ui.ConsoleUi.printError("Invalid category. Please try again.");
            }
        }

        // Show available allergens before parsing comma-separated input.
        ui.ConsoleUi.drawHeader("Available allergens");
        for (Allergen a : Allergen.values()) {
            System.out.println("  • " + a.name() + " — " + a.getDisplayName());
        }

        System.out.print("Enter allergens separated by commas (or press Enter to skip): ");
        String allergenInput = sc.nextLine().trim();

        Set<Allergen> allergens = new HashSet<>();
        if (!allergenInput.isEmpty()) {
            for (String part : allergenInput.split(",")) {
                try {
                    allergens.add(Allergen.valueOf(part.trim().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    ui.ConsoleUi.printError("Unknown allergen skipped: " + part.trim());
                }
            }
        }

        Dish dish = new Dish(name, price, allergens, category);
        boolean success = Manager.addDish(dish);

        if (success) {
            ui.ConsoleUi.printSuccess("Dish added successfully.");
        } else {
            ui.ConsoleUi.printError("Could not add dish. It may already exist.");
        }
    }

    private static void handleDeleteDish() {
        List<Dish> dishes = Manager.listDishes();
        if (dishes.isEmpty()) {
            ui.ConsoleUi.printInfo("No dishes found.");
            return;
        }

        ui.ConsoleUi.drawHeader("Current dishes in database");
        for (Dish d : dishes) {
            System.out.println("  • " + d.getName() + " (" + String.format("%.2f", d.getPrice()) + " €)");
        }

        System.out.print("\nType the dish name to delete: ");
        String dishName = sc.nextLine().trim();

        if (dishName.isEmpty()) {
            ui.ConsoleUi.printError("Dish name cannot be empty.");
            return;
        }

        boolean deleted = Manager.deleteDish(dishName);
        if (deleted) {
            ui.ConsoleUi.printSuccess("Dish deleted successfully.");
        } else {
            ui.ConsoleUi.printError("Could not delete dish. It may not exist or may be referenced by another table.");
        }
    }

    private static void handleDatabaseQueries() {
        boolean back = false;

        while (!back) {
            ui.ConsoleUi.drawBox("Database queries (read-only)", java.util.List.of(
                "1. List tables (service)",
                "2. List dishes (service)",
                "3. List menus (service)",
                "4. List reservations (service)",
                "5. List orders (service)",
                "6. SELECT * FROM order_items",
                "7. SELECT * FROM order_item_chosen_courses",
                "8. Custom SELECT query",
                "0. Back"
            ));

            int option = -1;
            while (option < 0 || option > 8) {
                try {
                    System.out.print("Enter your choice: ");
                    option = sc.nextInt();
                } catch (InputMismatchException e) {
                    ui.ConsoleUi.printError("Invalid input. Please enter a number between 0 and 8.");
                    sc.nextLine();
                }

                if (option < 0 || option > 8) {
                    ui.ConsoleUi.printError("Invalid choice. Please try again.");
                }
            }
            sc.nextLine();

            switch (option) {
                case 0:
                    back = true;
                    break;
                case 1:
                    printTablesFromService();
                    break;
                case 2:
                    printDishesFromService();
                    break;
                case 3:
                    printMenusFromService();
                    break;
                case 4:
                    printReservationsFromService();
                    break;
                case 5:
                    printOrdersFromService();
                    break;
                case 6:
                    executeAndPrintSelect("SELECT * FROM order_items");
                    break;
                case 7:
                    executeAndPrintSelect("SELECT * FROM order_item_chosen_courses");
                    break;
                case 8:
                    System.out.println("Type your SELECT query:");
                    String query = sc.nextLine().trim();
                    executeAndPrintSelect(query);
                    break;
                default:
                    ui.ConsoleUi.printError("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void printTablesFromService() {
        List<Table> tables = ws.listTables();
        if (tables.isEmpty()) {
            ui.ConsoleUi.printInfo("No tables found.");
            return;
        }

        ui.ConsoleUi.drawHeader("Tables");
        tables.forEach(System.out::println);
    }

    private static void printDishesFromService() {
        List<Dish> dishes = ws.listDishes();
        if (dishes.isEmpty()) {
            ui.ConsoleUi.printInfo("No dishes found.");
            return;
        }

        ui.ConsoleUi.drawHeader("Dishes");
        dishes.forEach(System.out::println);
    }

    private static void printMenusFromService() {
        List<Menu> menus = ws.listMenus();
        if (menus.isEmpty()) {
            ui.ConsoleUi.printInfo("No menus found.");
            return;
        }

        ui.ConsoleUi.drawHeader("Menus");
        menus.forEach(System.out::println);
    }

    private static void printReservationsFromService() {
        List<Reservation> reservations = rm.listReservations();
        if (reservations.isEmpty()) {
            ui.ConsoleUi.printInfo("No reservations found.");
            return;
        }

        ui.ConsoleUi.drawHeader("Reservations");
        reservations.forEach(System.out::println);
    }

    private static void printOrdersFromService() {
        List<Order> orders = ws.listOrders();
        if (orders.isEmpty()) {
            ui.ConsoleUi.printInfo("No orders found.");
            return;
        }

        ui.ConsoleUi.drawHeader("Orders");
        orders.forEach(System.out::println);
    }

    private static void executeAndPrintSelect(String query) {
        if (!isSelectOnlyQuery(query)) {
            ui.ConsoleUi.printError("Only single SELECT queries are allowed.");
            return;
        }

        try (Connection conn = JdbcConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(normalizeQuery(query));
             ResultSet rs = stmt.executeQuery()) {

            printResultSet(rs);

        } catch (SQLException e) {
            ui.ConsoleUi.printError("Query failed: " + e.getMessage());
        }
    }

    private static boolean isSelectOnlyQuery(String query) {
        if (query == null) {
            return false;
        }

        String normalized = query.trim().toLowerCase();
        if (normalized.isEmpty()) {
            return false;
        }

        if (!normalized.startsWith("select")) {
            return false;
        }

        // Prevent chaining multiple statements.
        int firstSemicolon = normalized.indexOf(';');
        if (firstSemicolon != -1 && firstSemicolon != normalized.length() - 1) {
            return false;
        }

        return !(normalized.contains(" insert ")
                || normalized.contains(" update ")
                || normalized.contains(" delete ")
                || normalized.contains(" drop ")
                || normalized.contains(" alter ")
                || normalized.contains(" create ")
                || normalized.contains(" truncate ")
                || normalized.contains(" grant ")
                || normalized.contains(" revoke "));
    }

    private static String normalizeQuery(String query) {
        String trimmed = query.trim();
        if (trimmed.endsWith(";")) {
            return trimmed.substring(0, trimmed.length() - 1);
        }
        return trimmed;
    }

    private static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        StringBuilder header = new StringBuilder();
        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) {
                header.append(" | ");
            }
            header.append(metaData.getColumnLabel(i));
        }

        System.out.println();
        System.out.println(header);
        System.out.println("-".repeat(Math.max(header.length(), 20)));

        int rowCount = 0;
        while (rs.next()) {
            StringBuilder row = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) {
                    row.append(" | ");
                }
                Object value = rs.getObject(i);
                row.append(value == null ? "NULL" : value.toString());
            }
            System.out.println(row);
            rowCount++;
        }

        if (rowCount == 0) {
            ui.ConsoleUi.printInfo("No rows returned.");
        } else {
            ui.ConsoleUi.printSuccess("Rows returned: " + rowCount);
        }
    }
}