package A003math;

/*
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
This is a Java code that calculates some formulas using the Java included library Math
*/
class Formulas {

//======================================================================================================================
// CONSTANTS
//======================================================================================================================

    private static final double A = 25.5;
    private static final double B = 50.67;
    private static final double C = 2;
    private static final double D = 10.5;
    private static final double E = -2.5;
    private static final double F = 13.6;
    private static final double G = 2.2;
    private static final double H = 3.141592653589793;
    private static final double I = 33.3;
    
    // Trygonometric values
    private static final double T = Math.toRadians(90); // Trygonometric functions only work with radians
    private static final double Z = Math.toRadians(45);
    
    public static void main(String[] args) {

//======================================================================================================================
// MAIN CODE
//======================================================================================================================

    double formula1 = (Math.sqrt(A) + (B / C)) / (D - (E / Math.pow(F, 2)));
    double formula2 = ((Math.pow(A, 2) / (B - C)) + (D - E) / (F - (G * H / I)));
    double formula3 = ((Math.pow(A, 2) - Math.pow(B, 3) - 4) * Math.sin(T) * Math.cos(Z) / (3 * C));
    double formula4 = ((Math.pow(A + B, 2) / C) - (3 * D / (E + F)) - Math.log10(7 * G));

//======================================================================================================================
// OUTPUT
//======================================================================================================================
        
    System.out.println("\nResult Formula 1 = " + formula1);
    System.out.println("Result Formula 2 = " + formula2);
    System.out.println("Result Formula 3 = " + formula3);
    System.out.println("Result Formula 4 = " + formula4 + "\n");
    
    }   
}
// END