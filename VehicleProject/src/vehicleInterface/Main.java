package vehicleInterface;

import java.util.Scanner;

/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is the main class for the vehicle project
 * Generated with AI assistance
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Car starts at position (0,0), with 1000 money, available, and 100 petrol
        Car myCar = new Car(true, 1000, 0, 0, 100);

        int selectedOption = -1;

        while (selectedOption != 0) {

            System.out.println();
            System.out.println("MENU:");
            System.out.println("1. MOVE CAR X");
            System.out.println("2. MOVE CAR Y");
            System.out.println("3. SHOW DETAILS CAR");
            System.out.println("4. SIMULATE COLLISION CAR");
            System.out.println("5. REPAIR CAR");
            System.out.println("6. REFILL CAR");
            System.out.println("7. GET MONEY CAR");
            System.out.println("0. LEAVE.");
            System.out.print("OPTION (0 .. 7) ? ");

            selectedOption = scanner.nextInt();

            System.out.println();

            if (selectedOption == 1) {

                boolean moved = myCar.move('x');

                if (moved) {
                    System.out.println("Move X Car " + myCar.toString());
                } else {
                    System.out.println("Cannot move X. Check availability or petrol.");
                }

            } else if (selectedOption == 2) {

                boolean moved = myCar.move('y');

                if (moved) {
                    System.out.println("Move Y Car " + myCar.toString());
                } else {
                    System.out.println("Cannot move Y. Check availability or petrol.");
                }

            } else if (selectedOption == 3) {

                System.out.println("Details Car " + myCar.toString());

            } else if (selectedOption == 4) {

                boolean collided = myCar.collision();

                if (collided) {
                    System.out.println("Collision Car " + myCar.toString());
                } else {
                    System.out.println("Cannot simulate collision. Car is already unavailable.");
                }

            } else if (selectedOption == 5) {

                boolean repaired = myCar.repair();

                if (repaired) {
                    System.out.println("Repair Car " + myCar.toString());
                } else {
                    System.out.println("Cannot repair. Car is available or not enough money.");
                }

            } else if (selectedOption == 6) {

                boolean refilled = myCar.refill();

                if (refilled) {
                    System.out.println("Refill Car " + myCar.toString());
                } else {
                    System.out.println("Cannot refill. Check availability, tank is full, or not enough money.");
                }

            } else if (selectedOption == 7) {

                myCar.setMoney(myCar.getMoney() + 100);
                System.out.println("Get Money (100$) Car " + myCar.toString());

            } else if (selectedOption == 0) {

                System.out.println("Goodbye!");

            } else {

                System.out.println("Invalid option. Please enter a number between 0 and 7.");

            }
        }

        scanner.close();
    }
}