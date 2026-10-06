package A007arrays1D;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
* int[] numbers = {1, 2, 3, 4, 5}; To declare the array right away.
* float[] array = new float[arraySize]; To declare the array with the desired size. array[i] to add.
 */


/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a Java code that get the input from the user and uses it to create an array of the desired size and elements.
 * After that the program will calculate and print the sum, max, mean and percentage of numbers bigger than the mean.
 * 
 * STEP BY STEP (Without error handling):
 * 1. Declare running constants.
 * 2. Create a Scanner object to read user input.
 * 3. Start a loop to run the program.
 * 4. Check if the user wants to exit the program.
 * 5. Get the number of elements of the array.
 * 6. Create the array.
 * 7. Fill the array with user input.
 * 8. Calculate the sum, max, mean and percentage of numbers bigger than the mean.
 * 9. Print the results.
 * 10. Ask the user if they want to run the program again, if "Yes" go back to step 3, if no go to step 11.
 * 11. Close the scanner and end the program.
 */
class Array1 {

//================================================================
// CONSTANTS
//================================================================

    private static final String MENU_MESSAGE = "\n MENU \n 1. Run Program \n 0. Exit\nChoose menu option: ";
    private static final byte EXIT_VALUE = 0;
    private static final byte RUN_VALUE = 1;
    private static final byte MIN_ARRAY_SIZE = 1;
    private static final byte MAX_ARRAY_SIZE = 127;

    public static void main(String[] args) {

//================================================================
// METHOD MAIN 
//================================================================

    // Initial variables and objects
    byte currentStatus = RUN_VALUE; // 1 = running, 0 = exit
    Scanner textInput = new Scanner(System.in);

    while (currentStatus == RUN_VALUE) {   // Program main loop

//-------------------------------------------------
// MENU AND ARRAY CREATION 
//-------------------------------------------------

        try {
            // Allows the user to run or exit the program
            currentStatus = constrainedByteInput(MENU_MESSAGE, textInput, EXIT_VALUE, RUN_VALUE);
            if (currentStatus == EXIT_VALUE) {break;}

            // Get the number of elements (between 1 and 127).
            byte arraySize = constrainedByteInput("Enter the number of items: ", textInput, MIN_ARRAY_SIZE, MAX_ARRAY_SIZE);

            // Create an array with the desired size.
            float[] array = new float[arraySize];

            // Insert values into the array
            array = dataEntry(array, arraySize, textInput);

            // Print array elements
            printData(array, arraySize);

//-------------------------------------------------
// CALCULATIONS AND PRINT OUTPUTS
//-------------------------------------------------

            // Calculate the statistics
            float sum = sumArray(array, arraySize);
            float max = arrayMaxValue(array, arraySize);
            float mean = meanArray(array, arraySize, sum);
            float percentageBiggerThanMean = greaterThanMean(array, arraySize, mean);

            // Print results
            System.out.println("\nTotal addition = " + sum);
            System.out.println("Max value = " + max);
            System.out.println("Mean = " + mean);
            System.out.printf("Percentage greater than mean = %.2f%%\n", percentageBiggerThanMean);

        } // END BLOCK try 

// -------------------------------------------------
// ERROR HANDLING
// -------------------------------------------------

        // Caught when input is different type than expected
        catch (InputMismatchException e) {System.out.println("\nInputMismatchException: " + e.getMessage() + "\nYou either did not enter a number or you entered a number out of range.") ;}

        finally {textInput.nextLine(); } // Clear the input buffer 

    } // END of WHILE loop: Program main loop

    textInput.close(); 
    System.out.println("Exiting program...\n");


} // END MAIN 



//================================================================
// METHODS 
//================================================================

    /**
     * This method takes a Scanner object and two byte objects as parameters
     * and returns a byte object. It prints the String object to the console
     * and then reads a byte from the console and returns it as the result if it is
     * within a given range.
     * 
     * @param message The String object to be printed to the console.
     * @param scannerName The Scanner object to read from.
     * @return The byte object read from the console.
     */
    public static byte byteInput (String message, Scanner scanner) {
        System.out.print(message);
        byte userChoice = scanner.nextByte();
        return userChoice;
    } 


    /**
     * This method takes a float array and a byte as parameters
     * and returns a float object. It sums all the elements of the array.
     * 
     * @param array The float array to be summed.
     * @param arraySize The number of elements in the array.
     * @return The sum of all the elements in the array.
     */
    public static float sumArray(float[] array, byte arraySize) {
        float sum = 0;
        for (int i = 0; i < arraySize; i++) {
            sum += array[i];
        }
        return sum;
    }


    /**
     * This method takes a float array and a byte as parameters
     * and returns a float object. It finds the maximum value in the array.
     * 
     * @param array The float array to find the maximum value of.
     * @param arraySize The number of elements in the array.
     * @return The maximum value in the array.
     */
    public static float arrayMaxValue (float[] array, byte arraySize) {
        float max = array[0];
        for (int i = 0; i < arraySize; i++) {
            max = Math.max(array[i], max); // if array[i] > max 
        }
        return max;
    }


    /**
     * This method takes a float array, a byte, and a float as parameters
     * and returns a float object. It calculates the mean of all the elements in the array.
     * 
     * @param array The float array to calculate the mean of.
     * @param arraySize The number of elements in the array.
     * @param sum The sum of all the elements in the array.
     * @return The mean of all the elements in the array.
     */
    public static float meanArray(float[] array, byte arraySize, float sum) {
        float mean = sum / arraySize;
        return mean;
    }


    /**
     * This method takes a float array, a byte, and a float as parameters
     * and returns a float object. It counts the number of elements in the array
     * that are greater than the given mean and returns the percentage of such elements.
     * 
     * @param array The float array to count the elements of.
     * @param arraySize The number of elements in the array.
     * @param mean The mean value of the array.
     * @return The percentage of elements in the array that are greater than the given mean.
     */
    public static float greaterThanMean(float[] array, byte arraySize, float mean) {
        byte counter = 0;
        for (int i = 0; i < arraySize; i++) {
            if (array[i] > mean) {
                counter++;
            }
        }
        return counter * 100 / arraySize;
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
     * This method takes a float array, an byte, and a Scanner object as parameters
     * and returns a float array. It asks the user to input the values of the array
     * and stores them in the array.
     * 
     * @param array The float array to be filled with user input.
     * @param arraySize The number of elements in the array.
     * @param textInput The Scanner object to read from.
     * @return The filled float array.
     */
    public static float[] dataEntry( float[] array ,byte arraySize, Scanner textInput) { 
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

} // END CLASS Array1
// END