package fileStream;

import java.io.*;
import java.nio.charset.Charset;

/**
 * ByteFile.java
 * Class for handling byte-level file operations.
 * This class handles binary data at the byte level, making it suitable
 * for copying any type of file (text, images, executables, etc.)
 * 
 * @author Uriel Nieves Silva (https://gitlab.com/a253119un)
 */
public class ByteFile {

    /**
     * Encoding: UTF-8
     * Reads a file byte by byte and returns its content as a String.
     * @param filePath: path of the file to read
     * @return String with the content of the file
     */
    public String readFile(String filePath) {

        String  fileContent = "";                      // Output string that accumulates all chars
        Charset encoding    = Charset.forName("UTF-8"); // Encoding used to interpret the bytes

        try {
            // Represents a connection to the file specified by the path
            FileInputStream fileInput = new FileInputStream(filePath);

            // Wraps FileInputStream to decode bytes into chars using UTF-8 encoding
            InputStreamReader streamReader = new InputStreamReader(fileInput, encoding);

            // Read the first char (returns its Unicode code, or -1 if EOF)
            int charCode = streamReader.read();

            // While it does not reach the End Of File (-1)
            while (charCode != -1) {
                char currentChar = (char) charCode;           // Convert the code to a char
                fileContent      = fileContent + currentChar; // Add char to the output string
                charCode         = streamReader.read();       // Read next char
            }

            // Close the resources
            streamReader.close();
            fileInput.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return fileContent;
    }

    /**
     * Reads a file byte by byte and returns the number of bytes in the file.
     * @param filePath: path of the file to read
     * @return number of bytes in the file
     */
    public int countBytes(String filePath) {

        int byteCount = 0; // Counter for the number of bytes

        try {
            // Represents a connection to the file specified by the path
            FileInputStream fileInput = new FileInputStream(filePath);

            // Read the first byte (returns its value 0-255, or -1 if EOF)
            int byteCode = fileInput.read();

            // While it does not reach the End Of File (-1)
            while (byteCode != -1) {
                byteCount = byteCount + 1;   // Increment counter
                byteCode  = fileInput.read(); // Read next byte
            }

            fileInput.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return byteCount;
    }

    /**
     * Copies a file byte by byte from source to destination.
     * Works for any file type (text, images, executables, etc.)
     * @param sourceFilePath: path of the file to read
     * @param destFilePath:   path of the file to write
     */
    public void copyFile(String sourceFilePath, String destFilePath) {

        try {
            // Represents a connection to the source file
            FileInputStream fileInput = new FileInputStream(sourceFilePath);

            // Represents a connection to the destination file (creates it if it doesn't exist)
            FileOutputStream fileOutput = new FileOutputStream(destFilePath);

            // Read the first byte (returns its value 0-255, or -1 if EOF)
            int byteCode = fileInput.read();

            // While it does not reach the End Of File (-1)
            while (byteCode != -1) {
                fileOutput.write(byteCode);   // Write the byte to the destination file
                byteCode = fileInput.read();  // Read next byte
            }

            fileInput.close();
            fileOutput.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
    }
}