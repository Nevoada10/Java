package A007arrays1D;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a Java class that calculates t
 * Additionally, it handles exceptions for invalid input and array size.
 */
class Array3 {

//===================================================
// CONSTANTS
//===================================================

    private static final String MENU_MESSAGE = "\n MENU \n 1. Run Program \n 0. Exit\nChoose menu option: ";
    private static final byte EXIT_VALUE = 0;
    private static final byte RUN_VALUE = 1;
    private static final byte MIN_ARRAY_SIZE = 1;
    private static final byte MAX_ARRAY_SIZE = 127;
    
    public static void main(String[] args) {

//==================================================
// METHOD MAIN 
//==================================================

    // Initial variables and objects
    byte runningStatus = RUN_VALUE;
    Scanner textInput = new Scanner(System.in);

    // Program main loop
    while (runningStatus == RUN_VALUE) {
            
// ------------------------------------------------
// ARRAY CREATION 
// ------------------------------------------------
        try {
            // Allows the user to run or exit the program
            runningStatus = constrainedByteInput(MENU_MESSAGE ,textInput, EXIT_VALUE, RUN_VALUE);
            if (runningStatus == EXIT_VALUE) {break;}

            // Get the number of elements of the array, must be between 1 and 127.
            byte arraySize = constrainedByteInput("Enter the number of items: ", textInput, MIN_ARRAY_SIZE, MAX_ARRAY_SIZE);

            // Create an array with the desired size
            double[] arrayNumbers = new double[arraySize];

            // Fill the array with user input
            arrayNumbers = dataEntry(arrayNumbers, arraySize, textInput);

            // Define the number of elements to sum
            byte numbersToSum = constrainedByteInput("The number of first numbers to add: ", textInput, runningStatus, arraySize);

// ------------------------------------------------
// CALCULATIONS & OUTPUT
// ------------------------------------------------

            // Sum the first array elements
            double resultSum = sumFirstElements(arrayNumbers, numbersToSum);

            // Print the sum
            System.out.println("The sum of the first " + numbersToSum + " numbers is = " + resultSum);

        } // END TRY block 

// -------------------------------------------------
// ERROR HANDLING
// -------------------------------------------------

            // Caught when input is different type than expected
            catch (InputMismatchException e) {System.out.println("\nInputMismatchException: " + e.getMessage() + "\nYou either did not enter a number or you entered a number out of range") ;}

            finally {textInput.nextLine(); } // Clear the input buffer every time the program runs

            } // END WHILE loop: Program main loop

        textInput.close();
        System.out.println("Exiting the program...\n");
    
        
} // END MAIN



//===================================================
// METHODS
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
     * This method takes a float array, a byte, and a Scanner object as parameters
     * and returns a float array. It asks the user to input the values of the array
     * and stores them in the array.
     * 
     * @param array The float array to be filled with user input.
     * @param arraySize The number of elements in the array.
     * @param textInput The Scanner object to read from.
     * @return The filled float array.
     */
    public static double[] dataEntry( double[] array ,byte arraySize, Scanner textInput) { 
        // Create a loop to get the values of the array
        for (int i = 0; i < arraySize; i++) {
            System.out.printf("Enter number [%d]: ", i);
            array[i] = textInput.nextFloat();
        }
        System.out.println("");
        return array; 
    }

    /**
     * This method takes a float array and a byte as parameters
     * and returns a float object. It sums the first numbersToSum elements of the array.
     * 
     * @param arrayNumbers The float array to sum the elements of.
     * @param numbersToSum The number of elements to sum in the array.
     * @return The sum of the first numbersToSum elements of the array.
     */
    public static double sumFirstElements(double[] arrayNumbers,byte numbersToSum) {
        
        double sum = 0;
        for ( int i = 0;i < numbersToSum; i++) {
            sum += arrayNumbers[i];      
        }
        return (double) sum;
    }

//===================================================
// End METHODS
//===================================================


} // END CLASS Array3
// END