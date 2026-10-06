import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * YoutubeDelay.java
 *
 * Problem:
 * We are watching a live stream, but we arrived late and the video is already
 * streaming. We change the video speed to ?x so we can catch up.
 * We want to know how much time (h m s) it will take to catch up,
 * considering that the live stream is always streaming at 1x speed.
 *
 * @author Uriel Neves Silva
 * @since  2026-02-22
 */
public class YoutubeDelay {

    // =========================================================
    // CONSTANTS
    // =========================================================

    /** The speed at which the live stream is always broadcasting */
    static private final int LIVE_SPEED = 0;


    // =========================================================
    // MAIN
    // =========================================================

    /**
     * Entry point of the program.
     * 1. Asks the user for the current video delay and playback speed.
     * 2. Calculates how long it will take to catch up to the live stream.
     * 3. Prints the result in h m s format.
     *
     * @param args (unused)
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nYoutube Delay Calculator\n");

    try {
        // INPUT: Current video delay
        int   delayHours   = getIntInput(scanner,   "Type current video delay hours: ");
        int   delayMinutes = getIntInput(scanner,   "Type current video delay minutes: ");
        int   delaySeconds = getIntInput(scanner,   "Type current video delay seconds: ");
        float videoSpeed   = getFloatInput(scanner, "Type video speed: ");

        // CALCULATION: Convert delay to seconds, then calculate catch up time
        int   totalDelayInSeconds  = delayHours * 3600 + delayMinutes * 60 + delaySeconds;
        float catchUpTimeInSeconds = catchUpTimeInSeconds(totalDelayInSeconds, videoSpeed);

        // OUTPUT: Break catch up time back into h m s
        int catchUpHours   = (int) catchUpTimeInSeconds / 3600;
        int catchUpMinutes = (int) (catchUpTimeInSeconds % 3600) / 60;
        int catchUpSeconds = (int) catchUpTimeInSeconds % 60;

        System.out.println("\nCatch up time: " + catchUpHours + "h " + catchUpMinutes + "m " + catchUpSeconds + "s\n");
    }
    catch (InputMismatchException e) { // Invalid input
        System.out.println("Error: " + e.getMessage());
    }
    catch (ArithmeticException e) { // Division by zero
        System.out.println("Error: " + e.getMessage());
    }
    catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    finally {
        scanner.close();
    }
}


    // =========================================================
    // HELPER METHODS
    // =========================================================

    /**
     * Prompts the user for an integer input.
     *
     * @param scanner: the Scanner object to read from
     * @param message: the prompt message to display
     * @return the integer entered by the user
     */
    static private int getIntInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    /**
     * Prompts the user for a float input.
     *
     * @param scanner: the Scanner object to read from
     * @param message: the prompt message to display
     * @return the float entered by the user
     */
    static private float getFloatInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextFloat();
    }

    /**
     * Calculates the time (in seconds) needed to catch up to the live stream.
     *
     * Formula: catchUpTime = totalDelay / (videoSpeed - liveSpeed)
     * Example: 600s delay at 1.5x speed → 600 / (1.5 - 1) = 1200s to catch up
     *
     * @param totalDelayInSeconds: the total delay in seconds
     * @param videoSpeed:          the playback speed chosen by the user
     * @return the catch up time in seconds
     */
    static private float catchUpTimeInSeconds(int totalDelayInSeconds, float videoSpeed) {
        float speedDiff = videoSpeed - LIVE_SPEED;
        return totalDelayInSeconds / speedDiff;
    }
}