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
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import model.enums.Allergen;
import model.service.LdapUserRepositoryProduction;
import model.service.ReservationManager;
import model.table.Reservation;
import model.table.Shift;
import model.table.Table;
import model.user.Customer;
import model.user.User;

/**
 * Console workflow for customer-facing actions such as creating, canceling,
 * and viewing reservations, plus viewing registered allergens.
 */
public class CustomerConsole {
    private static final Scanner sc = new Scanner(System.in);
    private static final LdapUserRepositoryProduction lurp = new LdapUserRepositoryProduction();
    private static ReservationManager rm;

    /**
     * Runs the customer menu loop for the authenticated user.
     *
     * @param user Logged-in customer user.
     * @param rm   Shared reservation manager.
     */
    public static void run(User user, ReservationManager rm) {
        CustomerConsole.rm = rm;
        boolean exit = false;

        String displayName = (user.getName() != null && !user.getName().isBlank()) ? user.getName() : user.getId();
        ui.ConsoleUi.drawHeader("Welcome, " + displayName);

        while (!exit) {
            displayCustomerMenu();
            int choice = getUserChoice();
            executeAction(user, choice);
            exit = shouldExit(choice);
        }
    }

    /**
     * Displays the main menu to the user.
     */
    private static void displayCustomerMenu() {
        ui.ConsoleUi.drawBox("Customer Menu", java.util.List.of(
            "1. Make reservation",
            "2. Cancel reservation",
            "3. See reservations",
            "4. See my allergies",
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
        while (choice < 0 || choice > 4) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                ui.ConsoleUi.printError("Invalid input. Please enter a number between 0 and 4.");
                sc.nextLine(); // discard the invalid input
                displayCustomerMenu(); // redisplay the menu
            }
            // Try again if choice is invalid
            if (choice < 0 || choice > 4) {
                ui.ConsoleUi.printError("Invalid choice. Please try again.");
                displayCustomerMenu();
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
    private static void executeAction(User user, int choice) {

        switch (choice) {
            case 0:
                ui.ConsoleUi.printInfo("Logging out...");
                break;
            case 1:
                handleAddReservation(user);
                break;
            case 2:
                handleCancelReservation(user);
                break;
            case 3:
                handleSeeReservations(user);
                break;
            case 4:
                handleSeeAllergies(user);
                break;
            default:
                ui.ConsoleUi.printError("Invalid choice. Please try again.");
                displayCustomerMenu();
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

    private static Shift askShift() {
        System.out.print(
                "\nEnter shift (1 for first lunch (12:00 - 14:00), 2 for second lunch (14:00 - 16:00), 3 for first dinner (19:00 - 21:00) and 4 for second dinner (21:00 - 23:00): ");

        int option = -1;

        while (option < 1 || option > 4) {
            option = sc.nextInt();
            if (option < 1 || option > 4) {
                ui.ConsoleUi.printError("Invalid option.");
            }
        }

        return switch (option) {
            case 1 -> Shift.LUNCH1;
            case 2 -> Shift.LUNCH2;
            case 3 -> Shift.DINNER1;
            case 4 -> Shift.DINNER2;
            default -> throw new IllegalArgumentException("Invalid shift.");
        };
    }

    private static void handleAddReservation(User user) {
        String customerId = user.getId();

        System.out.print("\nNumber of people: ");
        int numSeats = sc.nextInt();

        Shift shift = askShift();

        System.out.print("Enter the date (" + LocalDate.now() + "): ");
        LocalDate date = LocalDate.parse(sc.next());

        List<Table> availableTables = rm.getAvailableTables(shift, date, numSeats);

        if (availableTables.isEmpty()) {
            ui.ConsoleUi.printError("No available tables for the selected date, shift and number of people.");
            return;
        }

        ui.ConsoleUi.drawHeader("Available tables");
        availableTables.forEach(System.out::println);

        System.out.print("\nEnter the table ID: ");
        String tableId = sc.next();

        Customer customer = null;

        List<User> users = lurp.findAll();

        for (User u : users) {
            if (u.getId().equals(customerId)) {
                customer = (Customer) u;
                break;
            }
        }

        if (customer == null) {
            ui.ConsoleUi.printError("Customer not found.");
            return;
        }

        Table table = null;

        for (Table t : availableTables) {
            if (t.getId().equals(tableId)) {
                table = t;
                break;
            }
        }

        if (table == null) {
            ui.ConsoleUi.printError("Table not found.");
            return;
        }

        Reservation reservation = new Reservation(customer, table, numSeats, shift, date);

        ReservationManager.ReservationResult result = rm.addReservation(reservation);

        switch (result) {
            case SUCCESS:
                ui.ConsoleUi.printSuccess("Reservation added successfully.");
                break;
            case INVALID_RESERVATION:
                ui.ConsoleUi.printError("Reservation data is invalid. Please review your input and try again.");
                break;
            case NO_CAPACITY:
                ui.ConsoleUi.printError("The selected table cannot accommodate that number of people.");
                break;
            case PAST_DATE:
                ui.ConsoleUi.printError("You cannot create a reservation for a past date.");
                break;
            case SHIFT_CONFLICT:
                ui.ConsoleUi.printError("The selected table is already booked for that date and shift.");
                break;
            default:
                ui.ConsoleUi.printError("The reservation could not be saved due to a persistence error.");
                break;
        }
    }

    private static void handleCancelReservation(User user) {
        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation r : rm.getReservations()) {
            if (r.getCustomer().getId().equals(user.getId())) {
                userReservations.add(r);
            }
        }

        if (userReservations.isEmpty()) {
            ui.ConsoleUi.printInfo("You have no reservations to cancel.");
            return;
        }

        printReservationsPanel(userReservations);

        System.out.print("\nEnter the reservation ID to cancel: ");
        String id = sc.next();

        ReservationManager.ReservationResult result = rm.cancelReservation(id);

        switch (result) {
            case SUCCESS:
                ui.ConsoleUi.printSuccess("Reservation cancelled successfully.");
                break;
            case NOT_FOUND:
                ui.ConsoleUi.printError("Reservation not found. Please check the reservation ID.");
                break;
            case NOT_MODIFIABLE:
                ui.ConsoleUi.printError("This reservation can no longer be cancelled.");
                break;
            case PERSISTENCE_ERROR:
                ui.ConsoleUi.printError("The cancellation could not be saved due to a persistence error.");
                break;
            default:
                ui.ConsoleUi.printError("Invalid cancellation request.");
                break;
        }
    }

    private static void handleSeeReservations(User user) {
        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation r : rm.getReservations()) {
            if (r.getCustomer().getId().equals(user.getId())) {
                userReservations.add(r);
            }
        }

        if (userReservations.isEmpty()) {
            printReservationsEmptyState();
        } else {
            printReservationsPanel(userReservations);
        }
    }

    private static void printReservationsEmptyState() {
        String color = ui.ConsoleUi.YELLOW;
        String reset = ui.ConsoleUi.RESET;
        String bold = ui.ConsoleUi.BOLD;

        System.out.println();
        System.out.println(color + bold + "No Reservations Found" + reset);
        System.out.println(color + "- You have no reservations yet." + reset);
        System.out.println();
    }

    private static void printReservationsPanel(List<Reservation> reservations) {
        String color = ui.ConsoleUi.GREEN;
        String reset = ui.ConsoleUi.RESET;
        String bold = ui.ConsoleUi.BOLD;

        List<String> rows = new ArrayList<>();
        int maxLength = 0;
        int index = 1;
        for (Reservation reservation : reservations) {
            String row = "#" + index + " " + reservation;
            rows.add(row);
            if (row.length() > maxLength) {
                maxLength = row.length();
            }
            index++;
        }

        String separator = "-".repeat(Math.max(1, maxLength));

        System.out.println();
        System.out.println(color + bold + "Your Reservations" + reset);
        System.out.println(color + separator + reset);

        for (String row : rows) {
            System.out.println(color + row + reset);
        }

        System.out.println(color + separator + reset);
        System.out.println();
    }

    private static void handleSeeAllergies(User user) {
        java.util.Optional<User> userOpt = lurp.findByUid(user.getId());
        if (userOpt.isEmpty() || !(userOpt.get() instanceof Customer customer)) {
            ui.ConsoleUi.printError("Customer profile not found.");
            return;
        }

        Set<Allergen> allergens = customer.getAllergens();
        if (allergens == null || allergens.isEmpty()) {
            String color = ui.ConsoleUi.GREEN;
            String reset = ui.ConsoleUi.RESET;
            String bold = ui.ConsoleUi.BOLD;

            System.out.println();
            System.out.println(color + bold + "Your Allergies" + reset);
            System.out.println(color + "------------------------------" + reset);
            System.out.println(color + "- You have no registered allergies." + reset);
            System.out.println(color + "------------------------------" + reset);
            System.out.println();
            return;
        }

        String color = ui.ConsoleUi.GREEN;
        String reset = ui.ConsoleUi.RESET;
        String bold = ui.ConsoleUi.BOLD;

        List<String> rows = new ArrayList<>();
        int maxLength = 0;
        for (Allergen allergen : allergens) {
            String row = "- " + allergen.getDisplayName();
            rows.add(row);
            if (row.length() > maxLength) {
                maxLength = row.length();
            }
        }

        String separator = "-".repeat(Math.max(1, maxLength));

        System.out.println();
        System.out.println(color + bold + "Your Allergies" + reset);
        System.out.println(color + separator + reset);
        for (String row : rows) {
            System.out.println(color + row + reset);
        }
        System.out.println(color + separator + reset);
        System.out.println();
    }
}