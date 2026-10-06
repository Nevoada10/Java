package A005sequentialestructure1;

import java.util.Scanner;

/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a Java code that calculates the arithmetic, geometric, and harmonic means of four numbers.
 */
    class SeqEs14 {


//======================================================================================================================
// MAIN CODE
//======================================================================================================================


        public static void main(String[] args) {

        // Get keyboard input from the user
        Scanner userInput = new Scanner(System.in);
        double firstNumber = getInput(userInput, "\nEnter first number: ");
        double secondNumber = getInput(userInput, "Enter second number: ");
        double thirdNumber = getInput(userInput, "Enter third number: ");
        double fourthNumber = getInput(userInput, "Enter fourth number: ");
        userInput.close();

        // Results
        double arithmeticMeanResult = roundDecimals(calculateAhmeticMean(firstNumber, secondNumber, thirdNumber, fourthNumber),4);
        double geometricMeanResult = roundDecimals(calculateGeometricMean(firstNumber, secondNumber, thirdNumber, fourthNumber), 4);
        double harmonicMeanResult = roundDecimals(calculateHarmonicMean(firstNumber, secondNumber, thirdNumber, fourthNumber), 4);

        // Print results
        System.out.println("\nArithmetic Mean: " + arithmeticMeanResult);
        System.out.println("Geometric Mean: " + geometricMeanResult);
        System.out.println("Harmonic Mean: " + harmonicMeanResult + "\n");

        } // Main Method End
        

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
         * This method takes four double numbers as parameters and returns the arithmetic mean of them.
         * 
         * @param number1 The first number to be used in the calculation of the arithmetic mean.
         * @param number2 The second number to be used in the calculation of the arithmetic mean.
         * @param number3 The third number to be used in the calculation of the arithmetic mean.
         * @param number4 The fourth number to be used in the calculation of the arithmetic mean.
         * @return The arithmetic mean of the four numbers.
         */
    static double calculateAhmeticMean(double number1, double number2, double number3, double number4) {
        return ( number1 + number2 + number3 + number4 ) / 4;
    }


        /**
         * This method takes four double numbers as parameters and returns the geometric mean of them.
         * The geometric mean of a set of numbers is the nth root of the product of the numbers.
         * 
         * @param number1 The first number to be used in the calculation of the geometric mean.
         * @param number2 The second number to be used in the calculation of the geometric mean.
         * @param number3 The third number to be used in the calculation of the geometric mean.
         * @param number4 The fourth number to be used in the calculation of the geometric mean.
         * @return The geometric mean of the four numbers.
         */
    static double calculateGeometricMean(double number1, double number2, double number3, double number4) {
        return (double) Math.pow(number1 * number2 * number3 * number4, (double) 1/4);
    }

        /**
         * This method takes four double numbers as parameters and returns the harmonic mean of them.
         * The harmonic mean of a set of numbers is calculated by taking the reciprocal of the average of the reciprocals of each number.
         * 
         * @param number1 The first number to be used in the calculation of the harmonic mean.
         * @param number2 The second number to be used in the calculation of the harmonic mean.
         * @param number3 The third number to be used in the calculation of the harmonic mean.
         * @param number4 The fourth number to be used in the calculation of the harmonic mean.
         * @return The harmonic mean of the four numbers.
         */
    static double calculateHarmonicMean(double number1, double number2, double number3, double number4) {
        return 4 / (1/number1 + 1/number2 + 1/number3 + 1/number4);
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