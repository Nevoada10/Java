package A005sequentialestructure1;

import java.util.Scanner;

/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a Java code that calculates the area of a triangle.
 */
    class SeqEs13 {

        public static void main(String[] args) {


//======================================================================================================================
// MAIN CODE
//======================================================================================================================


        // Get user input
        Scanner userInput = new Scanner(System.in);
        double triangleHeight = getInput(userInput, "\nEnter height: ");
        double triangleBase = getInput(userInput, "Enter base: ");
        userInput.close();

        // Calculate area of the triangle
        double triangleArea = roundDecimals(calculateTriangleArea(triangleHeight, triangleBase),4) ;

        // Print the area result
        System.out.printf("Area of a triangle is: %.4f\n\n", triangleArea);

        } // Main method end

        
//======================================================================================================================
// METHODS
//======================================================================================================================


        /**
         * This method takes a Scanner object and a String object as parameters
         * and returns a double object. It prints the String object to the console
         * and then reads a double from the console and returns it as the result.
         * 
         * @param scannerName The Scanner object to read from.
         * @param stringMessage The String object to be printed to the console.
         * @return The double object read from the console.
         */
    static double getInput(Scanner scannerName  ,String stringMessage) {
        System.out.print(stringMessage);
        return scannerName.nextDouble();
    }


        /**
         * This method takes two double objects as parameters and returns a double object.
         * It calculates the area of a triangle given its height and base.
         * 
         * @param height The height of the triangle.
         * @param base The base of the triangle.
         * @return The area of the triangle.
         */
    static double calculateTriangleArea(double height, double base) {
        return (height * base / 2);
    }

        /**
         * Rounds a double value to the specified number of decimal places.
         * 
         * @param value The double value to be rounded.
         * @param numberOfDecimalPlaces The number of decimal places to round to.
         * @return The rounded double value.
         */
    static double roundDecimals(double value, int numberOfDecimalPlaces) {
        return (double) (Math.round(value * Math.pow(10, numberOfDecimalPlaces)) / Math.pow(10, numberOfDecimalPlaces));
    }
    
    
} // Main Class End
// END



    


