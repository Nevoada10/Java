package A001expressions;

/**
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
* This is a Java program that calculates the power in Watts using the formula POWER(W) = FORCE(kg*m/s^2 ) * DISTANCE(m) / TIME(s)
*/
class Power {

//*======================================================================================================================
//* CONSTANTS   
//*======================================================================================================================
      
    private static final byte FORCE = 125;    // FORCE in Newtows
    private static final byte DISTANCE = 37;  // DISTANCE in meters
    private static final byte TIME = 12;      // TIME in seconds

    public static void main(String[] args) {

//*======================================================================================================================
// MAIN CODE
//*======================================================================================================================
        
        // Calculate the power
        float power = (float) (FORCE * DISTANCE) / (TIME); // Cast to float

        System.out.printf("\nP (no decimals) = %d \n", (int) power);
        System.out.printf("P (with decimals) = %f \n",   power);
        System.out.printf("P (with 2 decimals) = %.2f\n\n", power);
        
    }
}
// END