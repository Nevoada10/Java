
package tests;

import java.util.Scanner;
import java.util.Arrays;


/*
* @author Uriel Neves Silva (https://gitlab.com/a253119un);
Challenge: this program can be made by defining just one integer variable, do you know how?
This is a Java code that:
1) Get the user input and sstore it in a string named numberString
2) Using delimitador as (";") ;Store the numbers in an array
3) Sort the array
4) Print the max and min values
*/

class Delimitador {

// ======================================================================================================================
// CONSTANTS
//======================================================================================================================

    private static final char LEFT_DOUBLE_QUOTES = (char)171; 
    private static final char RIGHT_DOUBLE_QUOTES = (char)187; 
    
    public static void main(String[] args) {

//======================================================================================================================
// INITIAL VARIABLES
//======================================================================================================================
    
    // Initiliaze variables
    int[] numbers;

    // Define expressions
    numbers = new int[6];
    
//======================================================================================================================
// MAIN CODE
//======================================================================================================================
    
    // 1) GETTING THE INPUT FROM THE USER 
    Scanner userInput = new Scanner(System.in);
    System.out.print("\nEnter 6 numbers separated by " + LEFT_DOUBLE_QUOTES + ";" + RIGHT_DOUBLE_QUOTES + ": ");
    String numberString = userInput.nextLine();

    // 2) STORING THE NUMBERS IN AN ARRAY 
    // Using a Scanner with a delimiter of ";"
    Scanner numberScanner = new Scanner(numberString).useDelimiter(";");

    // A loop that adds numbers to an array
    for (int i = 0; i < numbers.length; i++) { // [0 to 6);
        numbers[i] = numberScanner.nextInt(); 
    }

    userInput.close();
    numberScanner.close();

    // 3) SORTING THE NUMBERS IN ASCENDING ORDER ();
    Arrays.sort(numbers);

//======================================================================================================================
// OUTPUT
//======================================================================================================================

    // 4) PRINTING THE MAX AND MIN VALUES
    System.out.println("Max value: " + numbers[numbers.length - 1]);
    System.out.println("Min value: " + numbers[0] + "\n");
    }
}
