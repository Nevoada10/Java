package classActivities;

/** 
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
* This is a simple Java program that calculates a and round it.
*/
 class Math1 {

    private static final float A = 0.1f;
    private static final float B = 0.0003f;
    private static final float C = 3f;

    public static void main(String[] args) {
    
    // Calculations
    float result = A - B * C;

//======================================================================================================================
// OUTPUTS
//======================================================================================================================
    
    // Full result
    System.out.println("\nFull result: " + result + "\n");

    // Using round()
    System.out.println("2 decimals rounded: "+ Math.round(result * 100.0) / 100.0); 
    System.out.println("3 decimals rounded: "+ Math.round(result * 1000.0) / 1000.0); 
    System.out.println("4 decimals rounded: "+ Math.round(result * 10000.0) / 10000.0); 

    // Using %._f
    System.out.printf("\n2 decimals formatation: %.2f\n", result);
    System.out.printf("3 decimals formatation: %.3f\n", result);
    System.out.printf("4 decimals formatation: %.4f\n\n", result);

    }
}
// END
