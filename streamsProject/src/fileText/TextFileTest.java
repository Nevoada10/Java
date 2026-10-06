package fileText;

/**
 * TextFileTest.java (Main)
 * Demonstrates how to use the TextFile class to:
 * 1) Read a text file
 * 2) Count the number of chars of a text file
 * 3) Copy a text file
 * 4) Write a text at the end of a file (append mode)
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class TextFileTest {

    /*
     * ==========================================================================
     * CONSTANTS
     * ==========================================================================
     */

    // Define the file relative paths (./data) as constants
    static final String FILE_IN_TEXT  = "./data/thelittleprince.txt";
    static final String FILE_COPY_TEXT = "./data/thelittleprince-copy.txt";

    // Define the last line to write
    static final String LAST_LINE     = "This is the last line of the book. THE END";

    /*
     * ==========================================================================
     * MAIN METHOD
     * ==========================================================================
     */

    public static void main(String[] args) {

        printBox("PROGRAM STARTED");

        // Instantiate the TextFile class object
        TextFile textFile = new TextFile();

        // 1) Read file thelittleprince.txt and print it to console
        printBox("FILE CONTENT");
        String fileContent = textFile.readFile(FILE_IN_TEXT);
        System.out.println(fileContent);

        // 2) Count chars of file thelittleprince.txt
        printBox("CHAR COUNT RESULTS");
        int totalChars = textFile.countChars(FILE_IN_TEXT);
        System.out.println("Total chars in " + FILE_IN_TEXT + ": " + totalChars);

        // 3) Copy thelittleprince.txt to thelittleprince-copy.txt
        printBox("COPY FILE");
        textFile.copyFile(FILE_IN_TEXT, FILE_COPY_TEXT);
        System.out.println("File copied to: " + FILE_COPY_TEXT);

        // 4) Append the last line to the new file
        printBox("APPEND LAST LINE");
        textFile.writeFile(LAST_LINE, FILE_COPY_TEXT);
        System.out.println("Last line appended to: " + FILE_COPY_TEXT);

        printBox("PROGRAM COMPLETED SUCCESSFULLY");
    }

    /*
     * ==========================================================================
     * HELPER METHODS
     * ==========================================================================
     */

    /**
     * Prints a styled box with the given title
     * @param title The title to display in the box
     */
    private static void printBox(String title) {
        int boxWidth    = 54;
        int paddingSize = (boxWidth - title.length()) / 2;

        String leftPad  = " ".repeat(paddingSize);
        String rightPad = " ".repeat(paddingSize);

        System.out.println("\n" + """
                ╔══════════════════════════════════════════════════════════════╗
                  %s
                ╚══════════════════════════════════════════════════════════════╝
                """.formatted(leftPad + title + rightPad));
    }
}