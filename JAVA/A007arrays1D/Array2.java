package A007arrays1D;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This Java class creates an array with the desired size,
 * fills the array with user input, and then calculates the square, cube,
 * and fourth power of each element in the array and prints the results.
 * Additionally, it handles exceptions for invalid input and array size.
 */
class Array2 {

//===================================================
// CONSTANTS
//===================================================
    
    private static final String MENU_MESSAGE = "\n MENU \n 1. Run Program \n 0. Exit\nChoose menu option: ";
    private static final byte EXIT_VALUE = 0;
    private static final byte RUN_VALUE = 1;
    private static final byte MIN_ARRAY_SIZE = 1;
    private static final byte MAX_ARRAY_SIZE = 127;

    public static void main(String[] args) {

//===================================================
// MAIN METHOD
//===================================================

    // Initial variables and objects
    byte currentStatus = RUN_VALUE;
    Scanner textInput = new Scanner(System.in);

    while (currentStatus == RUN_VALUE) {  // Program main loop
    
// -------------------------------------------------
// MENU AND ARRAY CREATION 
// -------------------------------------------------

        try {
            // Allows the user to run or exit the program
            currentStatus = constrainedByteInput(MENU_MESSAGE ,textInput, EXIT_VALUE, RUN_VALUE);
            if (currentStatus == EXIT_VALUE) {
                break;
            }

            // Get the number of elements of the array [1, 127]
            byte arraySize = constrainedByteInput("Enter the number of items: ", textInput, MIN_ARRAY_SIZE, MAX_ARRAY_SIZE);

            // Create an array with the desired size.
            double[] arrayNumbers = new double[arraySize];

            // Insert values into the array
            arrayNumbers = dataEntry(arrayNumbers, arraySize, textInput);

            // Create an array to store the calculations for each number, because the exercise asks squareCubeFour to return an array of 3 values
            double[] arrayCalculations = new double[3];
            for (int i = 0; i < arraySize; i++) {  // For each element in the arrayNumbers, calculate the square, cube, and fourth power
                arrayCalculations = squareCubeFour(arrayNumbers[i]); // arrayCalculations[0] = square, arrayCalculations[1] = cube, arrayCalculations[2] = fourth
                System.out.printf("[%d] = %f  Square = %f  Cube = %f  Fourth = %f\n", i, arrayNumbers[i] ,arrayCalculations[0], arrayCalculations[1], arrayCalculations[2]);
            }

        } // END of TRY block 
            
//-------------------------------------------------
// ERROR HANDLING
//-------------------------------------------------

        // Caught when input is different type than expected
        catch (InputMismatchException e) {System.out.println("\nInputMismatchException: " + e.getMessage() + "\nYou either did not enter a number or you entered a number out of range") ;}

        finally {textInput.nextLine(); } // Clear the input buffer every time the program runs

        } // END of WHILE loop: Program main loop

    textInput.close();
    System.out.println("Exiting program...\n");


} // END MAIN



//===================================================
// METHODS
//===================================================


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
     * This method takes a float array and a byte as parameters
     * and prints all the elements of the array to the console.
     * It uses a for loop to iterate through the array and the printf method to print the elements.
     * The elements are printed in the format "[index] = value" where index is the position of the element
     * in the array and value is the value of the element.
     * 
     * @param array The float array to be printed.
     * @param arraySize The number of elements in the array.
     */
    public static void printData( float[] array ,byte arraySize){
        // Create a loop to print all of the array values
        for (int i = 0; i < arraySize; i++) {
        System.out.printf("[%d] = %f\n", i, array[i]);
        }
    }


    /**
     * For an element in the numberArray, calculate the square, cube, and fourth power.
     * 
     * @param x The element of the numberArray to calculate the square, cube, and fourth power of.
     * @return An array of size 3 containing the square, cube, and fourth power of x.
     */
    public static double[] squareCubeFour(double x){
        // For an element in the numberArray, calculate the square, cube, and fourth power
        double[] arrayCalculations = new double[3];

        arrayCalculations[0] = Math.pow(x, 2);
        arrayCalculations[1] = Math.pow(x, 3);
        arrayCalculations[2] = Math.pow(x, 4);

        return arrayCalculations; 
    }

} // END CLASS Array2
// END