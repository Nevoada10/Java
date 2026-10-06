package A008ProgModular.ticket;

// Imports related to date and time
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter; 

/**
 * This class provides methods for generating date and time strings, and for rounding
 * float values to a specified number of decimal places.
 * 
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Calculations {

// =======================================================================
// METHODS
// =======================================================================

    /**
     * Generates a string representing the current date in the format "dd/MM/yyyy".
     * 
     * @return A string representing the current date.
     */
    static String generateDate() {

        // Get the object representing the current date
        LocalDate date = LocalDate.now();

        // Define the pattern that should be followed to format the time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Format the time using the formatter
        String formattedDate = date.format(formatter);

        return formattedDate;
    }


    /**
     * Generates a string representing the current time in the format "HH:mm:ss".
     * 
     * @return A string representing the current time.
     */
    static String generateTime() {

        // Get the object representing the current time
        LocalTime time = LocalTime.now();

        // Define the pattern that should be followed to format the time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Format the time using the formatter
        String formattedTime = time.format(formatter);

        return formattedTime;
    }


    /**
     * Rounds a float value to the specified number of decimal places.
     * 
     * @param value The float value to be rounded.
     * @param numberOfDecimalPlaces The number of decimal places to round to.
     * @return The rounded float value.
     */
    public static float roundDecimals(float value, int numberOfDecimalPlaces) {
        float multiplier = (float) Math.pow(10, numberOfDecimalPlaces);
        return (float) (Math.round(value * multiplier) / multiplier);
    }

} // End Class Calculations
// END