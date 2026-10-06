package fileStream;

/**
 * FileStreamTest.java (Main)
 * Demonstrates how to use the ByteFile class to:
 * 1) Read a text file using FileInputStream
 * 2) Read an image file using FileInputStream
 * 3) Count the number of bytes of a file
 * 4) Copy an image file using FileInputStream and FileOutputStream
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class FileStreamTest {

    /**
     * ==========================================================================
     * CONSTANTS
     * ==========================================================================
     */

    // Define the file relative paths (./data) as constants
    static final String FILE_IN_TEXT   = "./data/thelittleprince.txt";
    static final String FILE_IN_IMAGE  = "./data/s.jpg";
    static final String FILE_OUT_IMAGE = "./data/s-copy.jpg";

    /*
     * ==========================================================================
     * MAIN METHOD
     * ==========================================================================
     */

    public static void main(String[] args) {

        printBox("PROGRAM STARTED");

        // Create an instance of ByteFile
        ByteFile byteFile = new ByteFile();

        // 1) Read a text file thelittleprince.txt using FileInputStream
        printBox("TEXT FILE CONTENT");
        String textContent = byteFile.readFile(FILE_IN_TEXT);
        System.out.println(textContent);

        // 2) Read an image file s.jpg using FileInputStream
        printBox("IMAGE FILE CONTENT");
        String imageContent = byteFile.readFile(FILE_IN_IMAGE);
        System.out.println("Image content: " + imageContent);

        // 3) Count bytes of thelittleprince.txt and s.jpg
        printBox("BYTE COUNT RESULTS");
        int textByteCount = byteFile.countBytes(FILE_IN_TEXT);
        System.out.println("Total bytes in " + FILE_IN_TEXT  + ": " + textByteCount);

        int imageByteCount = byteFile.countBytes(FILE_IN_IMAGE);
        System.out.println("Total bytes in " + FILE_IN_IMAGE + ": " + imageByteCount);

        // 4) Copy s.jpg to s-copy.jpg
        printBox("COPY IMAGE FILE");
        byteFile.copyFile(FILE_IN_IMAGE, FILE_OUT_IMAGE);
        System.out.println("Image copied to: " + FILE_OUT_IMAGE);

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