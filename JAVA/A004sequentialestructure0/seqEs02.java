package A004sequentialestructure0;

import java.util.Scanner;

/*
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
* This program reads two numbers from the standard input and calculates:
Their sum, difference, product, division, remainder, power, maximum and minimum. All methods are instance methods.
*/

class SeqEs02 {

//======================================================================================================================
// MAIN CODE
//======================================================================================================================

    public static void main(String[] args) {

        // Create an instance of the class
        SeqEs02 instance = new SeqEs02();

        //Declare variables to get input
        double firstNum;
        double secondNum;

        // Get keyboard input from user
        Scanner userInput = new Scanner(System.in);
        System.out.print("\nEnter the first number: ");
        firstNum = userInput.nextDouble();
        System.out.print("Enter the second number: ");
        secondNum = userInput.nextDouble();
        userInput.close();

        //Print results
        System.out.printf("\nAdd: %.3f\n", instance.roundDecimals(instance.sum(firstNum, secondNum), 3));
        System.out.printf("Subtract: %.3f\n", instance.roundDecimals(instance.difference(firstNum, secondNum), 3));
        System.out.println("Multiply: " + instance.multiply(firstNum, secondNum));
        System.out.println("Divide: " + instance.quotient(firstNum, secondNum));
        System.out.println("Power: " + instance.power(firstNum, secondNum));
        System.out.println("Maximum: " + instance.maximum(firstNum, secondNum));      
        System.out.println("Minimum: " + instance.minimum(firstNum, secondNum) + "\n");

    } // End of main method

//======================================================================================================================
// METHODS
//======================================================================================================================

    /**
     * This method takes two double numbers and returns their sum.
     * 
     * @param num1 The first double number to add.
     * @param num2 The second double number to add.
     * @return The sum of num1 and num2.
     */
    double sum(double num1, double num2) {
        return num1 + num2;
    }

    /**
     * This method takes two double numbers and returns their difference.
     * 
     * @param num1 The first double number to subtract.
     * @param num2 The second double number to subtract.
     * @return The difference of num1 and num2.
     */
    double difference(double num1, double num2) {
        return num1 - num2;
    }


    /**
     * This method takes two double numbers and returns their product.
     * 
     * @param num1 The first double number to multiply.
     * @param num2 The second double number to multiply.
     * @return The product of num1 and num2.
     */
    double multiply(double num1, double num2) {
        return num1 * num2;
    }


    /**
     * This method takes two double numbers and returns their quotient.
     * 
     * @param num1 The dividend.
     * @param num2 The divisor.
     * @return The quotient of num1 and num2.
     */
    double quotient(double num1, double num2) {
        return num1 / num2;
    }


    /**
     * This method takes two double numbers and returns their power.
     * 
     * @param num1 The base.
     * @param num2 The exponent.
     * @return The power of num1 and num2.
     */
    double power(double num1, double num2) {
        return (double) Math.pow(num1, num2);
    }


    /**
     * This method takes two double numbers and returns the minimum of both.
     * 
     * @param num1 The first double number.
     * @param num2 The second double number.
     * @return The minimum of num1 and num2.
     */
    double minimum(double num1, double num2) {
        return Math.min(num1, num2);
    }

    
    /**
     * This method takes two double numbers and returns the maximum of both.
     * 
     * @param num1 The first double number.
     * @param num2 The second double number.
     * @return The maximum of num1 and num2.
     */
    double maximum(double num1, double num2) {
        return Math.max(num1, num2);
    }

    /**
     * Rounds a double value to the specified number of decimal places.
     * 
     * @param value The double value to be rounded.
     * @param numberOfDecimalPlaces The number of decimal places to round to.
     * @return The rounded double value.
     */
    double roundDecimals(double value, int numberOfDecimalPlaces) {
        return (double) (Math.round(value * Math.pow(10, numberOfDecimalPlaces)) / Math.pow(10, numberOfDecimalPlaces));
    }

} // Class end
 // END