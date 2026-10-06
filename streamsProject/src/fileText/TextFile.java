package fileText;

import java.io.*;

/**
 * TextFile.java
 * Class to handle text file operations.
 * 
 * @author Uriel Nieves Silva (https://gitlab.com/a253119un)
 */
@SuppressWarnings("ConvertToTryWithResources")
public class TextFile {

    /**
     * Reads the content of a text file and returns it as a String.
     * @param filePath: path of the file to read
     * @return String with the content of the file
     */
    public String readFile(String filePath) {

        String fileContent = ""; // Output string that accumulates all lines

        try {
            // Represents a connection to the file specified by the path
            FileReader     fileReader     = new FileReader(filePath);

            // Wraps FileReader to read line by line efficiently
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read the first line
            String currentLine = bufferedReader.readLine();

            // While it does not reach the End Of File (null)
            while (currentLine != null) {
                fileContent = fileContent + currentLine + "\n"; // Add line + newline to the output
                currentLine = bufferedReader.readLine();        // Read next line
            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return fileContent;
    }

    /**
     * Reads a file char by char and returns the number of chars in the file.
     * @param filePath: path of the file to read
     * @return the number of chars in the file
     */
    public int countChars(String filePath) {

        int charCount = 0; // Counter for the number of chars

        try {
            // Represents a connection to the file specified by the path
            FileReader     fileReader     = new FileReader(filePath);

            // Wraps FileReader to read efficiently
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read the first char (returns its ASCII/Unicode code, or -1 if EOF)
            int charCode = bufferedReader.read();

            // While it does not reach the End Of File (-1)
            while (charCode != -1) {
                charCount = charCount + 1;    // Increment counter
                charCode  = bufferedReader.read(); // Read next char
            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return charCount;
    }

    /**
     * Writes a text at the end of a file (append mode).
     * @param text:     text to add at the end of the file
     * @param filePath: path of the file to write
     */
    public void writeFile(String text, String filePath) {

        boolean appendToEnd = true; // Opens the file in append mode (does not overwrite)

        try {
            // Represents a connection to the output file in append mode
            FileWriter fileWriter = new FileWriter(filePath, appendToEnd);

            // Write the text
            fileWriter.write(text);

            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    /**
     * Copies a text file char by char from source to destination.
     * @param sourceFilePath: path of the file to read
     * @param destFilePath:   path of the file to write
     */
    public void copyFile(String sourceFilePath, String destFilePath) {

        boolean overwriteContent = false; // Opens the output file in overwrite mode

        try {
            // Represents a connection to the source file
            FileReader     fileReader     = new FileReader(sourceFilePath);

            // Wraps FileReader to read efficiently
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Represents a connection to the destination file
            FileWriter fileWriter = new FileWriter(destFilePath, overwriteContent);

            // Read the first char (returns its ASCII/Unicode code, or -1 if EOF)
            int charCode = bufferedReader.read();

            // While it does not reach the End Of File (-1)
            while (charCode != -1) {
                char currentChar = (char) charCode;  // Convert the code to a char
                fileWriter.write(currentChar);       // Write the char to the output file
                charCode = bufferedReader.read();    // Read next char
            }

            bufferedReader.close();
            fileReader.close();
            fileWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
    }
}