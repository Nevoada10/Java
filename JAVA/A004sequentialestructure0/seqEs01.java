package A004sequentialestructure0;

import java.util.Scanner;

/*
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
* This program reads two numbers from the standard input and calculates:
Their sum, difference, product, quotient, remainder, power, maximum and minimum. All methods are static.
*/

class SeqEs01 {

//======================================================================================================================
// MAIN CODE
//======================================================================================================================

    public static void main(String[] args) {

    //Declare variables to get input
    int firstNum;
    int secondNum;

    // Get user input from keyboard
    Scanner userInput = new Scanner(System.in);
    System.out.print("\nEnter the first number: ");
    firstNum = userInput.nextInt();
    System.out.print("Enter the second number: ");
    secondNum = userInput.nextInt();
    userInput.close();

    //Print results
    System.out.println("\nAdd: " + sum(firstNum, secondNum));
    System.out.println("Subtract: " + difference(firstNum, secondNum));
    System.out.println("Multiply: " + multiply(firstNum, secondNum));
    System.out.println("Divide: " + quotient(firstNum, secondNum));
    System.out.println("Modul: " + (int)modul(firstNum, secondNum));
    System.out.println("Power: " + power(firstNum, secondNum));
    System.out.println("Minimum: " + minimum(firstNum, secondNum));   
    System.out.println("Maximum: " + maximum(firstNum, secondNum) + "\n" );      
    }


//======================================================================================================================
// METHODS
//======================================================================================================================

    /**
     * This method takes two integer numbers and returns their sum.
     * 
     * @param num1 The first integer number.
     * @param num2 The second integer number.
     * @return The sum of num1 and num2.
     */
    static int sum(int num1, int num2) {
        return num1 + num2;
    }


    /**
     * This method takes two integer numbers and returns their difference.
     * 
     * @param num1 The first integer number.
     * @param num2 The second integer number.
     * @return The difference of num1 and num2.
     */
    static int difference(int num1, int num2) {
        return num1 - num2;
    }


    /**
     * This method takes two integer numbers and returns their product.
     * 
     * @param num1 The first integer number.
     * @param num2 The second integer number.
     * @return The product of num1 and num2.
     */
    static int multiply(int num1, int num2) {
        return num1 * num2;
    }


    /**
     * This method takes two integer numbers and returns their quotient.
     * 
     * @param num1 The dividend.
     * @param num2 The divisor.
     * @return The quotient of num1 and num2.
     */
    static int quotient(int num1, int num2) {
        return  num1 /  num2;
    }


    /**
     * This method takes two integer numbers and returns their remainder.
     * 
     * @param num1 The dividend.
     * @param num2 The divisor.
     * @return The remainder of num1 and num2.
     */
    static float modul(int num1, int num2) {
        return num1 % num2;
    }


    /**
     * This method takes two float numbers and returns their power.
     * 
     * @param num1 The base.
     * @param num2 The exponent.
     * @return The power of num1 and num2.
     */
    static float power(float num1, float num2) {
        return (float) Math.pow(num1, num2);
    }


    /**
     * This method takes two integer numbers and returns the minimum of both.
     * 
     * @param num1 The first integer number.
     * @param num2 The second integer number.
     * @return The minimum of num1 and num2.
     */
    static int minimum(int num1, int num2) {
        return Math.min(num1, num2);
    }

   
    /**
     * This method takes two integer numbers and returns the maximum of both.
     * 
     * @param num1 The first integer number.
     * @param num2 The second integer number.
     * @return The maximum of num1 and num2.
     */
    static int maximum(int num1, int num2) {
        return Math.max(num1, num2);
    }

} // Main class end
//END