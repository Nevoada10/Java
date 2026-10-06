package A005sequentialestructure1;
import java.util.Scanner;
/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a Java code that gets input from the user and prints the final price of a product.
 */
class SeqEs15 {

//======================================================================================================================
// CONSTANTS
//======================================================================================================================

    private static final double TAX_IN_PERCENTAGE = 21;
    private static final String EURO_SYMBOL = "\u20AC";

//======================================================================================================================
// MAIN CODE
//======================================================================================================================

    public static void main(String[] args) {
       
        // Create an instance of the class
        SeqEs15 instance = new SeqEs15();
        
        // Get keyboard input from the user
        Scanner userInput = new Scanner(System.in);
        String productName = instance.getStringInput(userInput, "\nEnter the name of the product: ");
        double priceWithoutTax = instance.getDoubleInput(userInput, "Price without TAX: ");
        double discountInPercentage = instance.getDoubleInput(userInput, "Enter the discount in %: ");
        userInput.close();
        
        // Result
        double finalPrice = instance.roundDecimals(instance.calculateProductFinalPrice(priceWithoutTax, discountInPercentage),2);
        
        // Print results
        System.out.printf("The final price of the %s is: %.2f %s \n\n", productName, finalPrice, EURO_SYMBOL);
    } // Main Method End
    
//======================================================================================================================
// FUNCTIONS
//======================================================================================================================
    /**
     * This method takes a Scanner object and a String object as parameters
     * and returns a double object. It prints the String object to the console
     * and then reads a double from the console and returns it as the result.
     * 
     * @param scannerName The Scanner object to read from.
     * @param stringMessage The String object to be printed to the console.
     * @return The double object read from the console.
     */
    double getDoubleInput(Scanner scannerName, String stringMessage) {
        System.out.print(stringMessage);
        return scannerName.nextDouble();
    }
    
    /**
     * This method takes a Scanner object and a String object as parameters
     * and returns a String object. It prints the String object to the console
     * and then reads a line from the console and returns it as the result.
     * 
     * @param scannerName The Scanner object to read from.
     * @param stringMessage The String object to print to the console.
     * @return A String object containing the line read from the console.
     */
    String getStringInput(Scanner scannerName, String stringMessage) {
        System.out.print(stringMessage);
        return scannerName.nextLine();
    }
    
    /**
     * This method takes two double numbers and returns the final price of a product.
     * The first parameter is the price without TAX and the second parameter is the discount in percentage.
     * 
     * @param priceWithoutTax The price of the product without TAX.
     * @param discountInPercentage The discount in percentage of the product.
     * @return The final price of the product.
     */
    double calculateProductFinalPrice(double priceWithoutTax, double discountInPercentage) {
        return priceWithoutTax * ( 1 + ( TAX_IN_PERCENTAGE / 100 ) ) * ( 1 - ( discountInPercentage / 100 ));
    }
    
    /**
     * Rounds a double value to the specified number of decimal places.
     * 
     * @param value The double value to be rounded.
     * @param numberOfDecimalPlaces The number of decimal places to round to.
     * @return The rounded double value.
     */
    double roundDecimals(double value, int numberOfDecimalPlaces) {
        double multiplier = Math.pow(10, numberOfDecimalPlaces);
        return Math.round(value * multiplier) / multiplier;
    }
} // Main Class End
// END