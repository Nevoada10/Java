package classActivities;

/** 
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
* This is a simple Java program that calculates a result.
*/

class Math2 {

//======================================================================================================================
// CONSTANTS
//======================================================================================================================

    private static final float PRICE = 0.573f;
    private static final int QUANTITY = 14;
    private static final float DISCOUNT = 0.003f;

//======================================================================================================================
// MAIN CODE
//======================================================================================================================

    public static void main(String[] args) {

        // Declarations
        float result;

        // Operations
        result = ( PRICE - DISCOUNT ) * QUANTITY;

        // Output
        System.out.println("\nResult: " + result + "\n");

    }
}
// END