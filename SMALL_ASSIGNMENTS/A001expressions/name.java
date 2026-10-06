package A001expressions;

/** 
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
* This is a Java program that rewrites special characters in a text using ASCII conversion.
* Reference: ASCII Table: https://www.asciitable.com/
*/

class Name {

//*======================================================================================================================
//* CONSTANTS
//*======================================================================================================================

    // Declaring special characters
    private static final char COPYRIGHT_SYMBOL = (char)169; 
    private static final char TRADEMARK = (char)174; 
    private static final char LEFT_DOUBLE_QUOTES = (char)171; 
    private static final char RIGHT_DOUBLE_QUOTES = (char)187; 
    private static final char ONE_HALF_FRACTION = (char)189; 
    private static final char DEGREE_SYMBOL = (char)176;

    public static void main(String[] args) {

//*======================================================================================================================
//* OUTPUT
//*======================================================================================================================  

    // Print using the ASCII special character variables
    System.out.println("\n" + COPYRIGHT_SYMBOL + " Uriel Neves " + TRADEMARK);
    System.out.println("" + LEFT_DOUBLE_QUOTES + ONE_HALF_FRACTION + " of the World is suffering from malnutrition" + RIGHT_DOUBLE_QUOTES);
    System.out.println(LEFT_DOUBLE_QUOTES + "The average temperature of the planet is 13" + DEGREE_SYMBOL + "C" + RIGHT_DOUBLE_QUOTES + "\n");
    
    // OBS: The word "temperature" was written incorrectly on the original PDF document, so I fixed it.
    
    }
}
// END