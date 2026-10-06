package ui;

import java.util.List;

/**
 * Shared console rendering helper for boxes, headers, and status messages.
 */
public class ConsoleUi {
    /** ANSI reset code. */
    public static final String RESET = "\u001B[0m";
    /** ANSI cyan color code. */
    public static final String CYAN = "\u001B[36m";
    /** ANSI green color code. */
    public static final String GREEN = "\u001B[32m";
    /** ANSI red color code. */
    public static final String RED = "\u001B[31m";
    /** ANSI yellow color code. */
    public static final String YELLOW = "\u001B[33m";
    /** ANSI bold style code. */
    public static final String BOLD = "\u001B[1m";

    /**
     * Draws a boxed menu with a centered title and option rows.
     *
     * @param title   Menu title.
     * @param options Menu option lines.
     */
    public static void drawBox(String title, List<String> options) {
        int width = 56;
        for (String opt : options) {
            width = Math.max(width, opt.length() + 6);
        }
        
        // Print top border
        System.out.println(CYAN + "╔" + "═".repeat(width - 2) + "╗" + RESET);
        
        // Print centered title
        int titlePadding = (width - 2 - title.length()) / 2;
        String leftPad = " ".repeat(Math.max(0, titlePadding));
        String rightPad = " ".repeat(Math.max(0, width - 2 - title.length() - leftPad.length()));
        System.out.println(CYAN + "║" + BOLD + leftPad + title.toUpperCase() + RESET + CYAN + rightPad + "║" + RESET);
        
        // Print divider
        System.out.println(CYAN + "╠" + "═".repeat(width - 2) + "╣" + RESET);
        
        // Print options
        for (String opt : options) {
            String content = "  " + opt;
            String rightSpaces = " ".repeat(Math.max(0, width - 2 - content.length()));
            System.out.println(CYAN + "║" + RESET + content + rightSpaces + CYAN + "║" + RESET);
        }
        
        // Print bottom border
        System.out.println(CYAN + "╚" + "═".repeat(width - 2) + "╝" + RESET);
    }

    /**
     * Draws a highlighted one-line header box.
     *
     * @param text Header text.
     */
    public static void drawHeader(String text) {
        int width = 56;
        width = Math.max(width, text.length() + 6);
        
        System.out.println(CYAN + "╔" + "═".repeat(width - 2) + "╗" + RESET);
        int padding = (width - 2 - text.length()) / 2;
        String leftPad = " ".repeat(Math.max(0, padding));
        String rightPad = " ".repeat(Math.max(0, width - 2 - text.length() - leftPad.length()));
        System.out.println(CYAN + "║" + BOLD + leftPad + text + RESET + CYAN + rightPad + "║" + RESET);
        System.out.println(CYAN + "╚" + "═".repeat(width - 2) + "╝" + RESET);
    }

    /**
     * Prints a success message with success formatting.
     *
     * @param msg Message to display.
     */
    public static void printSuccess(String msg) {
        System.out.println(GREEN + " ✔ " + msg + RESET);
    }

    /**
     * Prints an error message with error formatting.
     *
     * @param msg Message to display.
     */
    public static void printError(String msg) {
        System.out.println(RED + " ✘ " + msg + RESET);
    }

    /**
     * Prints an informational message with info formatting.
     *
     * @param msg Message to display.
     */
    public static void printInfo(String msg) {
        System.out.println(YELLOW + " ℹ " + msg + RESET);
    }
}
