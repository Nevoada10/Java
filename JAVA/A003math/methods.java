
package A003math;
/*
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
This is a Java code that calculates some numbers using the Java included library Math
*/
class Methods {

//======================================================================================================================
// CONSTANTS
//======================================================================================================================
    
    // Exercise 1a
    private static final float NUMBER_TO_ROUND1 = 4.3f;
    private static final float NUMBER_TO_ROUND2 = 4.6f;
    private static final float NUMBER_TO_ROUND3 = 157.9f;

    // Exercise 1b, 1c
    private static final float FLOOR_N_CEIL1 = 1024.7f;
    private static final float FLOOR_N_CEIL2 = 10.3f;
    private static final float FLOOR_N_CEIL3 = -129.10f;
    private static final float FLOOR_N_CEIL4 = -25.92f;

    // Exercise 1d, 1e
    private static final float MAX_N_MIN1 = 24.26f;
    private static final float MAX_N_MIN2 = 24.75f;

    // Exercise 1f
    private static final int NUMBER_TO_SQRT1 = 1936;
    private static final int NUMBER_TO_SQRT2 = 16384;

    // Exercise 1g
    private static final int BASE_TEN_NUMBER1 = 10;
    private static final int BASE_TEN_NUMBER2 = 100;
    private static final float BASE_TEN_NUMBER3 = 10000.25f;

    // Exercise 1h
    private static final double BASE_E_NUMBER1 = 2.718281828459045;
    private static final float BASE_E_NUMBER2 = 154.7f;

    // Exercise 1i
    private static final float NUMBER_ABS1 = 35.26f;
    private static final float NUMBER_ABS2 = -12.75f;


    public static void main(String[] args) {
    
//======================================================================================================================
// MAIN CODE - OUTPUTS
//======================================================================================================================

    // 1a) Round() the values and print
    System.out.println("\nQuestion 1a -> Round()");
    System.out.println("Round: " + NUMBER_TO_ROUND1 + " = " + Math.round(NUMBER_TO_ROUND1));
    System.out.println("Round: " + NUMBER_TO_ROUND2 + " = " + Math.round(NUMBER_TO_ROUND2));
    System.out.println("Round: " + NUMBER_TO_ROUND3 + " = " + Math.round(NUMBER_TO_ROUND3));

    // 1b) Ceil() the values and print
    System.out.println("\nQuestion 1b -> Ceil()");
    System.out.println("Round up: " + FLOOR_N_CEIL1 + " : " + (int)Math.ceil(FLOOR_N_CEIL1));
    System.out.println("Round up: " + FLOOR_N_CEIL2 + " : " + (int)Math.ceil(FLOOR_N_CEIL2));
    System.out.println("Round up: " + FLOOR_N_CEIL3 + " : " + (int)Math.ceil(FLOOR_N_CEIL3));
    System.out.println("Round up: " + FLOOR_N_CEIL4 + " : " + (int)Math.ceil(FLOOR_N_CEIL4));

    // 1c Floor() the values and print
    System.out.println("\nQuestion 1c -> Floor()");
    System.out.println("Round down: " + FLOOR_N_CEIL1 + " : " + (int)Math.floor(FLOOR_N_CEIL1));
    System.out.println("Round down: " + FLOOR_N_CEIL2 + " : " + (int)Math.floor(FLOOR_N_CEIL2));
    System.out.println("Round down: " + FLOOR_N_CEIL3 + " : " + (int)Math.floor(FLOOR_N_CEIL3));
    System.out.println("Round down: " + FLOOR_N_CEIL4 + " : " + (int)Math.floor(FLOOR_N_CEIL4));

    // 1d) Print the max() between two values
    System.out.println("\nQuestion 1d -> Max()");
    System.out.println("Max = " + Float.max(MAX_N_MIN1, MAX_N_MIN2));

    // 1e) Print the min() between two values
    System.out.println("\nQuestion 1e -> Min()");
    System.out.println("Min = " + Float.min(MAX_N_MIN1, MAX_N_MIN2));

    // 1f) Print the square root
    System.out.println("\nQuestion 1f -> sqrt()");
    System.out.println("Square root of " + NUMBER_TO_SQRT1 + " : " + Math.sqrt(NUMBER_TO_SQRT1));
    System.out.println("Square root of " + NUMBER_TO_SQRT2 + " : " + Math.sqrt(NUMBER_TO_SQRT2));

    // 1g) Print the logarithm in base 10
    System.out.println("\nQuestion 1g -> log10()");
    System.out.println("Logarithm in base 10 of " + BASE_TEN_NUMBER1 + " : " + Math.log10(BASE_TEN_NUMBER1));
    System.out.println("Logarithm in base 10 of " + BASE_TEN_NUMBER2 + " : " + Math.log10(BASE_TEN_NUMBER2));
    System.out.println("Logarithm in base 10 of " + BASE_TEN_NUMBER3 + " : " + Math.log10(BASE_TEN_NUMBER3));
    
    // 1h) Print the logarithm in base e
    System.out.println("\nQuestion 1h -> log()");
    System.out.println("Logarithm in base e of " + BASE_E_NUMBER1 + " : " + Math.log(BASE_E_NUMBER1));
    System.out.println("Logarithm in base e of " + BASE_E_NUMBER2 + " : " + Math.log(BASE_E_NUMBER2));
 
    // 1i) Print the absolute value 
    System.out.println("\nQuestion 1i -> abs()");
    System.out.println("Absolute value of " + NUMBER_ABS1 + " : " + Math.abs(NUMBER_ABS1));
    System.out.println("Absolute value of " + NUMBER_ABS2 + " : " + Math.abs(NUMBER_ABS2));

    // 1j) Print 3 random int numbers between 0 and 10
    System.out.println("\nQuestion 1j -> random()* ");
    System.out.println("Random int numbers between 0 and 10:");
    for ( int i = 0; i < 3; i++) {
        System.out.print( (int) (Math.random()*11)  + " | ");
    }
    System.out.println(""); // Line break

    // 1k) Print 3 random int numbers between 1 and 100
    System.out.println("\nQuestion 1k -> random()* +");
    System.out.println("Random int numbers between 1 and 100:");
    for ( int i = 0; i < 3; i++) {
        System.out.print((int) (Math.random() * 100) + 1 + " | ");
    }
    System.out.println(""); // Line break

    // 1l) Print 3 random int numbers between 10 and 20
    System.out.println("\nQuestion 1l -> random()* +");
    System.out.println("Random int numbers between 10 and 20:");
    for ( int i = 0; i<3; i++) {
        System.out.print( (int)(Math.random() * 11) + 10 + " | "  );
    }
    System.out.println(""); // Line break

    // 1m Print 3 random int numbers between 12 and 45
    System.out.println("\nQuestion 1m -> random()* +");
    System.out.println("Random int numbers between 12 and 45:");
    for ( int i = 0; i < 3; i++ ) {
         System.out.print( (int)(Math.random() * 34) + 12 + " | "  );
    }
    System.out.println("\n"); // Line break
    
    }
}
// END
