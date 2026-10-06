package A002scanner;

import java.util.Scanner;
import java.util.Locale;

/**
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
* REQUIREMENTS: You can only declare a primitive type variable and an object of the Scanner class.
* This is a java code that receives two strings of numbers in different formats and prints the average of each one.
*/

class Average {

//======================================================================================================================
// CONSTANTS
//======================================================================================================================

    private static final String STRING_1 = "34 45 670 120 24 50.50 100.25 125.30 1250.15 250.35"; 
    private static final String STRING_2 = "34 45 670 120 24 50,50 100,25 125,30 1250,15 250,35"; 
    private static final int NUMBER_OF_ELEMENTS = 10;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

//======================================================================================================================
// MAIN CODE
//======================================================================================================================
    float sumString1 = 0;
    float sumString2 = 0;

    // Instantiating the scanner on STRING_1
    Scanner scanner = new Scanner(STRING_1);

    // Summing the numbers on the first string
    while (scanner.hasNextFloat()) {
        sumString1 += scanner.nextFloat(); 
    }
    scanner.close(); 


    // Reassigning the scanner on STRING_2
    scanner = new Scanner(STRING_2);
    scanner.useLocale(new Locale("es", "ES"));  // Replacing decimal format using comma

    // Summing the numbers on the second string
    while (scanner.hasNextFloat()) {
        sumString2 += scanner.nextFloat(); 
    }
    scanner.close(); 

//======================================================================================================================
// OUTPUT
//======================================================================================================================

    // Printing averages
    System.out.printf("\nAverage of string1: %.3f\n", (sumString1 / NUMBER_OF_ELEMENTS));
    System.out.printf("Average of string2: %.3f\n\n", (sumString2 / NUMBER_OF_ELEMENTS));
    }
}
