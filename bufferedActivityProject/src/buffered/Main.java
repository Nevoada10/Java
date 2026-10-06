package buffered;

/**
 * Main.java
 * Class for file operations using Buffered classes
 * 
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @since 2026-02
 */
public class Main {

    /*
     * ========================================
     * CONSTANTS
     * ========================================
     */

    // File paths (input)
    private static final String FILE_IN_TEXT = "./data/thelittleprince.txt";
    private static final String FILE_IN_IMAGE = "./data/drawing.jpg";

    // File paths (output)
    private static final String FILE_OUT_TEXT = "./data/thelittleprince-copy.txt";
    private static final String FILE_OUT_IMAGE = "./data/drawing-copy.jpg";
    
    /*
     * ========================================
     * MAIN METHOD
     * ========================================
     */

    public static void main(String[] args) {
        // Instantiate class FileBuffer
        FileBuffer fb = new FileBuffer();

        // Read file thelittleprince.txt using BufferedReader
        printBox("Reading file thelittleprince.txt");
        System.out.println(fb.readFile(FILE_IN_TEXT));

        // Count lines of file thelittleprince.txt using BufferedReader
        printBox("Counting section");
        System.out.println("Number of lines: " + fb.countLines(FILE_IN_TEXT) + " in " + FILE_IN_TEXT);
       
        // Count characters of file thelittleprince.txt using BufferedReader
        System.out.println("Number of characters: " + fb.countChars(FILE_IN_TEXT) + " in " + FILE_IN_TEXT);

        // Copy thelittleprince.txt file to new file thelittleprince-copy.txt using BufferedOutputStream
        fb.copyByte(FILE_IN_TEXT, FILE_OUT_TEXT);

        /* Write String into thelittleprince-copy.txt using BufferedWriter
         * \nThis is the end of the book.\nThanks for Reading it.
         */
        printBox("Writing section");
        fb.write("Hello World\nThis is the end of the book.\nThanks for Reading it.", FILE_OUT_TEXT);
        System.out.println("File " + FILE_OUT_TEXT + " written successfully!");

        //Copy prince.jpg file to new file drawing-copy.jpg using BufferedOutputStream
        printBox("Copying image");
        fb.copyByte(FILE_IN_IMAGE, FILE_OUT_IMAGE);
        System.out.printf("Image %s copied to %s successfully!\n\n", FILE_IN_IMAGE, FILE_OUT_IMAGE);
    }
    
    /*
     * ========================================
     * HELPER METHODS
     * ========================================
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