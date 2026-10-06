package A007arrays1D;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

/**
 * @author Uriel  Neves Silva (https://gitlab.com/a253119un)
 * This is a java class that:
 * Option 0: exits the program
 * Option 1: gets input from the user and uses it to overwrite an array of 10 elements.
 * Option 2: checks if there are repeated entries in the array.
 * Option 3: searches for an specific value in the array.
 */
class Array4 {

//===================================================
// CONSTANTS
//===================================================

    private static final String MENU_MESAGE = "MENU \n 1. Enter data \n 2. Repeated entries? \n 3. Search value \n 0. Quit  \nChoose menu option: ";
    private static final byte EXIT_VALUE = 0;
    private static final byte ENTER_DATA_OPTION = 1;
    private static final byte REPEATED_ENTRIES_OPTION = 2;
    private static final byte SEARCH_VALUE_OPTION = 3;
    private static final byte ARRAY_LENGTH = 10;

    public static void main(String[] args) {

//===================================================
// MAIN METHOD
//===================================================

        // Initialize variables and objects
        byte currentStatus = -1; 
        Scanner userInput = new Scanner(System.in);

        // Create an array with the specified length
        double[] numbersArray = new double[ARRAY_LENGTH];
        
        while (currentStatus != EXIT_VALUE) {

// ------------------------------------------------
// MENU DISPLAY
// ------------------------------------------------

        // Print all the numbers in the array
        System.out.println("\nAll the numbers in the array: ");
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            System.out.print(numbersArray[i] + " ; ");
        }
        System.out.println("\n");

            try {

                // Force the user to select an option from the menu, until a valid option is selected
                currentStatus = constrainedByteInput(MENU_MESAGE ,userInput, EXIT_VALUE, SEARCH_VALUE_OPTION);
                if (currentStatus == EXIT_VALUE) {break;}
                
                // If the user selects option 1, insert numbers in the array
                if (currentStatus == ENTER_DATA_OPTION) {
                    numbersArray = dataEntry(userInput, ARRAY_LENGTH);
                    // Sort the array, useful for option 2, because equal entries are next to each other
                    Arrays.sort(numbersArray);
                }

                // Else if the user selects option 2, check for repeated entries in the array
                else if (currentStatus == REPEATED_ENTRIES_OPTION) {
                    areRepeatedEntries(numbersArray);
                }

                // Else if the user selects option 3, search for a value in the array
                else if (currentStatus == SEARCH_VALUE_OPTION) { 
                    System.out.print("Number to search: ");
                    double searchedNumber = userInput.nextDouble();
                    numberOfTimes( numbersArray, searchedNumber);
                }
    
            } // END try block

// -------------------------------------------------
// ERROR HANDLING
// -------------------------------------------------

            // Caught when input is different type than expected
            catch (InputMismatchException e) {System.out.println("\nInputMismatchException: " + e.getMessage() + "\nYou either did not enter a number or you entered a number out of range") ;}

            finally { userInput.nextLine();}// Clean the buffer

        } // END while loop: Program main loop

    userInput.close(); 
    System.out.println("Exiting program...\n");


} // END MAIN



//===================================================
// METHODS / FUNCTIONS
//===================================================


    /**
     * This method takes a string message, a Scanner object, and two byte objects as parameters
     * and returns a byte object. It prints the string message to the console and then
     * reads a byte from the console and returns it as the result if it is
     * within a given range.
     * 
     * @param message The string message to be printed to the console.
     * @param textInput The Scanner object to read from.
     * @param minimumValue The minimum value of the range.
     * @param maximumValue The maximum value of the range.
     * @return The byte object read from the console.
     */
    public static byte constrainedByteInput(String message, Scanner textInput, byte minimumValue, byte maximumValue) {
        byte userChoice;
        // Create a loop to get constrained & persistent user input
            while (true) {
                System.out.print(message);
                userChoice = textInput.nextByte();
                if (userChoice >= minimumValue && userChoice <= maximumValue) {
                    System.out.println("");
                    return userChoice;
                }
            } 
        }


    /**
     * This method takes a Scanner object and a byte as parameters
     * and returns a double array. It asks the user to input the values of the array
     * and stores them in the array.
     * 
     * @param userInput The Scanner object to read from.
     * @param arrayLength The number of elements in the array.
     * @return The filled double array.
     */
    public static double[] dataEntry(Scanner userInput, byte arrayLength) {

        double[] array = new double[arrayLength];

        for ( int i = 0; i < arrayLength; i++)
        {
            System.out.printf("Enter number [%d]: ", i);
            array[i] = userInput.nextDouble();
            
        }
        return array;
    }

    
    /**
     * Checks if there are repeated entries in the given array.
     * 
     * @param array The array to check for repeated entries.
     * @return true if there are repeated entries, false otherwise.
     */
    public static boolean areRepeatedEntries(double[] array) {
        double previousNumber = array[0];
        // Check if there are repeated entries in the array, if so, break the loop and return a print
        for (int i = 1; i < array.length; i++) {
            double currentNumber = array[i];
            if (previousNumber == currentNumber) {
                System.out.println("There are repeated entries.");
                return true;
            }
            previousNumber = currentNumber;           
        }
        System.out.println("There are no repeated entries");
        return false;
    }

    
    /**
     * This method takes a float array and a double as parameters
     * and prints the number of times the given double appears in the array.
     * 
     * @param array The float array to search for the given double.
     * @param number The double to search for in the array.
     */
    public static void numberOfTimes(double[] array, double number) {
        byte counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                counter++;
            }
        }
        System.out.println( number + " appears " + counter + " times.");
        return; 
        }

} // END CLASS Array4
// END