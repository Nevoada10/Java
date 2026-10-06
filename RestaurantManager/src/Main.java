/*
 * CKU.java 2026-04-10
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

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import model.enums.Allergen;
import model.service.CustomerService;
import model.service.LdapUserRepositoryProduction;
import model.service.ReservationManager;
import model.user.User;
import ui.CustomerConsole;
import ui.ManagerConsole;
import ui.WaiterConsole;

/**
 * /src/CKU.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 *
 *         Entry point and top-level navigation for the restaurant management
 *         console application.
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final LdapUserRepositoryProduction lurp = new LdapUserRepositoryProduction();
    private static final CustomerService cs = new CustomerService();
    private static final ReservationManager rm = new ReservationManager();

    /**
     * Starts the application loop and dispatches user actions from the main menu.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getUserChoice();
            executeAction(choice);
            exit = shouldExit(choice);
        }
        sc.close();
    }

    /**
     * Displays the main menu to the user.
     */
    private static void displayMainMenu() {
        ui.ConsoleUi.drawBox("Restaurant Management System", java.util.List.of(
            "0. Exit",
            "1. Login (staff or customers already registered ) ",
            "2. Autoregister (new customers)"
        ));
        System.out.println();
    }

    /**
     * Reads and validates the user's menu choice from standard input.
     *
     * @return The selected menu option.
     */
    private static int getUserChoice() {
        int choice = -1;
        while (choice < 0 || choice > 2) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                ui.ConsoleUi.printError("Invalid input. Please enter 0, 1 or 2.");
                sc.nextLine(); // discard the invalid input
                displayMainMenu(); // redisplay the menu
            }
            // Try again if choice is invalid
            if (choice < 0 || choice > 2) {
                ui.ConsoleUi.printError("Invalid choice. Please try again.");
                displayMainMenu();
            }

        }
        sc.nextLine();
        return choice;
    }

    /**
     * Executes the corresponding action based on the user's choice.
     *
     * @param choice the user's choice
     */
    private static void executeAction(int choice) {
        switch (choice) {
            case 0:
                ui.ConsoleUi.printInfo("Exiting the program. Goodbye!");
                System.exit(0);
                break;
            case 1:
                handleLogin();
                break;
            case 2:
                handleAutoregister();
                break;
            default:
                ui.ConsoleUi.printError("Invalid choice. Please try again.");
                displayMainMenu();

        }
    }

    /**
     * Routes the user to the appropriate menu based on their role.
     *
     * @param user the authenticated user
     */
    private static void routeToMenu(User user) {
        switch (user.getRole()) {
            case CUSTOMER -> CustomerConsole.run(user, rm);
            case WAITER -> WaiterConsole.run(user, rm);
            case MANAGER -> ManagerConsole.run(user, rm);
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

    /**
     * Handles the login process for existing staff and registered customers.
     * Prompts for user ID and password, authenticates against LDAP, and routes
     * to the appropriate menu based on the user's role.
     */
    private static void handleLogin() {
        System.out.print("Enter the user ID: ");
        String uid = sc.nextLine();
        System.out.print("Enter the user password: ");
        String password = sc.nextLine();

        ui.ConsoleUi.printInfo("Logging in...");

        if (!lurp.authenticate(uid, password)) {
            ui.ConsoleUi.printError("Invalid credentials. Please try again.");
            return;
        }

        User user = lurp.findByUid(uid).get();
        routeToMenu(user);
    }

    /**
     * Handles the auto-registration process for new customers.
     * Collects customer ID, name, password, and allergen preferences from input.
     * Validates that required fields are not empty and attempts to register the customer.
     */
    private static void handleAutoregister() {
        ui.ConsoleUi.drawHeader("Create customer account");
        System.out.println("Please fill in the following fields:");
        System.out.println("- ID: unique login (example: marta01)");
        System.out.println("- Name: your full name (example: Marta Garcia)");
        System.out.println("- Password: password for your account");

        String customerId = null;
        while (customerId == null) {
            System.out.print("\nType your ID: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                ui.ConsoleUi.printError("ID cannot be empty.");
            } else {
                customerId = input;
            }
        }

        String name = null;
        while (name == null) {
            System.out.print("Type your full name: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                ui.ConsoleUi.printError("Name cannot be empty.");
            } else {
                name = input;
            }
        }

        String password = null;
        while (password == null) {
            System.out.print("Type your password: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                ui.ConsoleUi.printError("Password cannot be empty.");
            } else {
                password = input;
            }
        }

        ui.ConsoleUi.drawHeader("Available allergens");

        for (Allergen a : Allergen.values()) {
            System.out.println("  • " + a.name() + " — " + a.getDisplayName());
        }
        System.out.print("\nEnter your allergens separated by commas (or press Enter to skip): ");
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

        ui.ConsoleUi.drawHeader("Confirm registration");
        System.out.println("ID: " + customerId);
        System.out.println("Name: " + name);
        if (allergens.isEmpty()) {
            System.out.println("Allergens: none");
        } else {
            System.out.println("Allergens: " + allergens);
        }

        String confirmation;
        while (true) {
            System.out.print("Create this account? (Y/N): ");
            confirmation = sc.nextLine().trim().toUpperCase();
            if ("Y".equals(confirmation) || "N".equals(confirmation)) {
                break;
            }
            ui.ConsoleUi.printError("Invalid answer. Please type Y or N.");
        }

        if ("N".equals(confirmation)) {
            ui.ConsoleUi.printInfo("Registration cancelled.");
            return;
        }

        ui.ConsoleUi.printInfo("Registration in progress...");

        boolean success = cs.autoregister(customerId, name, password, allergens);

        if (success) {
            ui.ConsoleUi.printSuccess("Registration successful.");
        } else {
            ui.ConsoleUi.printError("Registration failed. Please try again.");
        }

    }
}