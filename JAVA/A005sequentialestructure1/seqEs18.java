package A005sequentialestructure1;

import java.util.Scanner;
/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a Java code that converts degrees in Fahrenheit to degrees in Celsius and vice versa.
 */
class SeqEs18 {

//======================================================================================================================
// MAIN CODE
//======================================================================================================================

    public static void main(String[] args) {

        // Create an instance of the class
        SeqEs18 instance = new SeqEs18();
        Scanner userInput = new Scanner(System.in);  // Get keyboard input from the user
       

        // Get temperature in Fahrenheit
        float temperatureInFahrenheit = instance.getFloatInput(userInput, "\nEnter the degrees in Fahrenheit: ");

        // Calculating and printing Fahrenheit to Celsius
        float resultInCelsius = instance.calculateFahrenheitToCelsius(temperatureInFahrenheit);
        System.out.printf("The degrees in Celsius are: %.2f°C\n", resultInCelsius);

        // Get temperature in Celsius
        float temperatureInCelsius = instance.getFloatInput(userInput, "\nEnter the degrees in Celsius: ");

        userInput.close(); // Close the scanner
        
        // Calculating and printing Celsius to Fahrenheit
        float resultInFahrenheit = instance.calculateCelsiusToFahrenheit(temperatureInCelsius);
        System.out.printf("The degrees in Fahrenheit are: %.2f°F\n\n", resultInFahrenheit);
        

    } // Main method end
    
//======================================================================================================================
// METHODS
//======================================================================================================================

    /**
     * This method takes a float temperature in Celsius as a parameter
     * and returns a float object with the equivalent temperature in Fahrenheit.
     * 
     * @param temperatureInCelsius The temperature in Celsius to be converted to Fahrenheit.
     * @return The temperature in Fahrenheit equivalent to the parameter.
     */
    float calculateCelsiusToFahrenheit(float temperatureInCelsius) {
        return 9 * temperatureInCelsius / 5 + 32; 
    }
    /**
     * This method takes a Scanner object and a String object as parameters
     * and returns a float object. It prints the String object to the console
     * and then reads a float from the console and returns it as the result.
     * 
     * @param scannerName The Scanner object to read from.
     * @param stringMessage The String object to be printed to the console.
     * @return The float object read from the console.
     */
    
    /**
     * This method takes a Scanner object and a String object as parameters
     * and returns a float object. It prints the String object to the console
     * and then reads a float from the console and returns it as the result.
     * 
     * @param scannerName The Scanner object to read from.
     * @param stringMessage The String object to be printed to the console.
     * @return The float object read from the console.
     */
    float getFloatInput(Scanner scannerName, String stringMessage) {
        System.out.print(stringMessage);
        return scannerName.nextFloat();
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
     * This method takes a float temperature in Fahrenheit as a parameter
     * and returns a float object with the equivalent temperature in Celsius.
     * 
     * @param temperatureInFahrenheit The temperature in Fahrenheit to be converted to Celsius.
     * @return The temperature in Celsius equivalent to the parameter.
     */
    float calculateFahrenheitToCelsius(float temperatureInFahrenheit) {
        return (temperatureInFahrenheit - 32) * 5 / 9;
    }
    
} // Main class end