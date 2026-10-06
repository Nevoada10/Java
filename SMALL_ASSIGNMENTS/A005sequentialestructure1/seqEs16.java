package A005sequentialestructure1;

import java.util.Scanner;
/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a Java code that calculates the discount in percentage of a product.
 */
class SeqEs16 {

//======================================================================================================================
// MAIN CODE
//======================================================================================================================
    public static void main(String[] args) {

        // Create an instance of the class
        SeqEs16 instance = new SeqEs16();

        // Get keyboard input from the user
        Scanner userInput = new Scanner(System.in);
        double fullPrice = instance.getDoubleInput(userInput, "\nEnter the price without discount: ");
        double priceWithDiscount = instance.getDoubleInput(userInput,"Enter the price with discount: ");
        userInput.close();
        
        // Result
        double discountInPercentage = instance.roundDecimals(instance.calculateDiscountInPercentage(fullPrice, priceWithDiscount),2);
        
        // Print results
        System.out.printf("The discount in percentage is: %.2f%%\n\n", discountInPercentage);
    } // Main Method End

//======================================================================================================================
// METHODS
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
     * @param stringMessage The String object to be printed to the console.
     * @return The String object read from the console.
     */
    String getStringInput(Scanner scannerName, String stringMessage) {
        System.out.print(stringMessage);
        return scannerName.nextLine();
    }
    

    /**
     * This method takes two double numbers as parameters and returns the discount in percentage of a product.
     * The first parameter is the price of the product without discount and the second parameter is the price of the product with discount.
     * 
     * @param fullPrice The price of the product without discount.
     * @param priceWithDiscount The price of the product with discount.
     * @return The discount in percentage of the product.
     */
    double calculateDiscountInPercentage(double fullPrice, double priceWithDiscount) {
        return 100 * (1 - priceWithDiscount / fullPrice);
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