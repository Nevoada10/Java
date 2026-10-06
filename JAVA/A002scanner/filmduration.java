package A002scanner;

import java.util.Scanner;
import java.util.Locale;

/**
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
* REQUIREMENTS: Print the results with 2 decimals
* This is a Java code that gets input from the user and prints the the number of frames and the length of a film in metters.
*/

class FilmDuration {

//======================================================================================================================
// CONSTANTS
//======================================================================================================================
    
    // Special characters in ASCII
    private static final char LEFT_DOUBLE_QUOTES = (char)171; 
    private static final char RIGHT_DOUBLE_QUOTES = (char)187; 

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

//======================================================================================================================
// MAIN CODE
//======================================================================================================================

        // User input variables
        byte framesPerSecond;
        long movieDurationInMinutes;
        float frameWidthInMillimeters;
        String movieTitle;

        // First print at the top
        System.out.println("\nFilm duration");

        // Instantiate the scanner object to get user input
        Scanner userInput = new Scanner(System.in);
        userInput.useLocale(new Locale("es", "ES")); // Set the locale to Spain -> So user can use "," instead of "." as delimiter

        // Getting input from the user
        System.out.print("Enter frames per second fps: ");
        framesPerSecond = userInput.nextByte(); 
        System.out.print("Enter duration of film in minutes: ");
        movieDurationInMinutes = userInput.nextLong();
        System.out.print("Enter the size one frame (width) in mm: ");
        frameWidthInMillimeters = userInput.nextFloat();
        userInput.nextLine();  // Consume the "\n" leftover newline
        System.out.print("Enter the name of the film: ");
        movieTitle = userInput.nextLine();
        userInput.close(); // Scanner closed

        // Calculating results with conversions
        int totalFrames = (int) (framesPerSecond * movieDurationInMinutes * 60);
        float movieLengthInMeters = (frameWidthInMillimeters/1000) * framesPerSecond * (movieDurationInMinutes * 60); 

// ======================================================================================================================
// OUTPUTS
//=======================================================================================================================

        // Print the results
        System.out.printf("\nTotal frames of %c%s%c: %d frames\n", LEFT_DOUBLE_QUOTES, movieTitle, RIGHT_DOUBLE_QUOTES, totalFrames);
        System.out.printf("Movie length: %.2f meters\n\n", movieLengthInMeters);

    }
}
// END