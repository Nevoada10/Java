package A005sequentialestructure1;

import java.util.Scanner;

/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a Java code that converts days, hours, minutes and seconds to seconds.
 */
class SeqEs17 {

//======================================================================================================================
// MAIN CODE
//======================================================================================================================

    public static void main(String[] args) {

    // Get user input
    Scanner userInput = new Scanner(System.in);
    int numberOfDays = getIntInput(userInput, "\nEnter the number of days: ");
    int numberOfHours = getIntInput(userInput, "Enter the number of hours: ");
    int numberOfMinutes = getIntInput(userInput, "Enter the number of minutes: ");
    int numberOfSeconds = getIntInput(userInput, "Enter the number of seconds: ");
    userInput.close();

    // Calculate conversions to seconds
    int daysToSeconds = calculateDaysToSeconds(numberOfDays);
    int hoursToSeconds = calculateHoursToSeconds(numberOfHours);
    int minutesToSeconds = calculateMinutesToSeconds(numberOfMinutes);
    // Seconds do not need to be converted

    //Print results
    System.out.println("\ndToS: " + daysToSeconds +" seconds");
    System.out.println("hToS: " + hoursToSeconds +" seconds");
    System.out.println("mToS: " + minutesToSeconds +" seconds");
    System.out.println("mToS: " + (minutesToSeconds + numberOfSeconds) +" seconds");
    System.out.println("hmsToS: " + (hoursToSeconds + minutesToSeconds + numberOfSeconds) +" seconds");
    System.out.println("dhmsToS:" + (daysToSeconds + hoursToSeconds + minutesToSeconds + numberOfSeconds) +" seconds\n");

    } // Main method end

//======================================================================================================================
// METHODS
//======================================================================================================================

    /**
     * This method takes a Scanner object and a String object as parameters
     * and returns an int object. It prints the String object to the console
     * and then reads an int from the console and returns it as the result.
     * 
     * @param scannerName The Scanner object to read from.
     * @param stringMessage The String object to be printed to the console.
     * @return The int object read from the console.
     */
    static int getIntInput(Scanner scannerName, String stringMessage) {
        System.out.print(stringMessage);
        return scannerName.nextInt();
    }

    /**
     * This method takes an integer number of minutes as a parameter
     * and returns the equivalent number of seconds.
     * 
     * @param numberOfMinutes The number of minutes to be converted to seconds.
     * @return The number of seconds equivalent to the parameter.
     */
    static int calculateMinutesToSeconds (int numberOfMinutes) {
        return numberOfMinutes * 60;
    }

    /**
     * This method takes an integer number of hours as a parameter
     * and returns the equivalent number of seconds.
     * 
     * @param numberOfHours The number of hours to be converted to seconds.
     * @return The number of seconds equivalent to the parameter.
     */
    static int calculateHoursToSeconds  (int numberOfHours) {
        return 60 * calculateMinutesToSeconds(numberOfHours);
    }

    /**
     * This method takes an integer number of days as a parameter
     * and returns the equivalent number of seconds.
     * 
     * @param numberOfDays The number of days to be converted to seconds.
     * @return The number of seconds equivalent to the parameter.
     */
    static int calculateDaysToSeconds  (int numberOfDays) {
        return 24 * calculateHoursToSeconds(numberOfDays);
    }

    
} // Main class end

