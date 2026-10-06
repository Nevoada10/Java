package A002scanner;

import java.util.Scanner;

/* 
%d: para enteros decimales. 
%f: para números de punto flotante (decimales). 
%s: para cadenas de texto. 
%b: para valores booleanos. 
%c: para caracteres. 
*/

/*
@author Uriel Neves Si  lva (https://gitlab.com/a253119un)
Challenge: this program can be made by defining just one integer variable, do you know how? ARRAY
This is a Java code that finds the max and min values in a string of numbers separated by ";".
*/

class Delimitador {

// =====================================================================================================================
// CONSTANTS
//======================================================================================================================

    // ASCII codes
    private static final char LEFT_DOUBLE_QUOTES = (char)171; 
    private static final char RIGHT_DOUBLE_QUOTES = (char)187; 

//======================================================================================================================
// MAIN CODE
//======================================================================================================================   
    public static void main(String[] args) {
        
        // Instantiating the first scanner
        Scanner userInput = new Scanner(System.in);
    
        // Store the userInput in a string
        System.out.print("\nEnter 6 numbers separated by " + LEFT_DOUBLE_QUOTES + ";" + RIGHT_DOUBLE_QUOTES + ": ");
        String numberString = userInput.nextLine(); 

        // Instantiating the second Scanner with a delimiter of ";" to read the numberString
        Scanner numberScanner = new Scanner(numberString).useDelimiter(";");

        // Defining loop initial variables
        int number = numberScanner.nextInt(); // First number
        int max = number;
        int min = number;

        while (numberScanner.hasNextInt()) {
            number = numberScanner.nextInt(); // Second to last
            max = Math.max(number, max);  
            min = Math.min(number, min);  
        }
    
        userInput.close();
        numberScanner.close();

//======================================================================================================================
// OUTPUT
//======================================================================================================================

        System.out.println("Max value: " + max);
        System.out.println("Min value: " + min + "\n");
    }
}
// END