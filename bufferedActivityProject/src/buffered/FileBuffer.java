package buffered;

import java.io.*;

/**
 * FileBuffer.java
 * Class for file operations using Buffered classes
 * Reads, writes and copies files
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class FileBuffer {

/*
=========================================
METHODS 
=========================================
*/
    /**
     * Reads a file and returns its content as a String
     * Uses BufferedReader to read line by line
     * 
     * @param file Path of the file to read
     * @return String containing the file content
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public String readFile(String fileIn) {
        
        try {
            FileReader content = new FileReader(fileIn);
            BufferedReader reader = new BufferedReader(content);
            String lineIn = reader.readLine();
            String lineOut = "";    

            while (lineIn != null) {
                lineIn = reader.readLine();
                lineOut += lineIn;
            }
            
            reader.close();

            return lineOut;
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Error reading file");
        return null;
    }

    /**
     * Displays a specific line of a file
     * Uses BufferedReader to read line by line
     * 
     * @param fileIn Path of the file to read
     * @param lineNumber Number of the line to read (starting at 1)
     * @return String containing the line content
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public String displaySpecificLine(String fileIn, int lineNumber) {
        
        try {
            FileReader content = new FileReader(fileIn);
            BufferedReader reader = new BufferedReader(content);
            String lineIn = reader.readLine();
            String lineOut = "";    
            int counter = 1;

            while (lineIn != null) {
                lineIn = reader.readLine();
                if (counter == lineNumber) {
                    lineOut = lineIn;
                    break;
                }
                counter++;
            }

            reader.close();
            return "'" + lineOut + "'";
            

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Error reading file");
        return null;
}

    /**
     * Counts the number of lines in a file
     * Uses FileReader to open the file and BufferedReader to read line by line
     * @param filePath Path of the file to read
     * @return Number of lines in the file
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public int countLines(String filePath) {
        int lineNumber = 0;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                line = bufferedReader.readLine();
                lineNumber++;
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return lineNumber;
    }

    /**
     * Counts the number of characters in a file
     * Uses the read() method
     * @param filePath Path of the file to read
     * @return Number of lines in the file
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public int countChars(String filePath) {
        int charNumber = 0;
        try {
            
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int character = bufferedReader.read();

            while ( character != -1) {
                charNumber++;
                character = bufferedReader.read();
            }

            bufferedReader.close();

            return charNumber;

        } 
        
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return -1;
    }

    

    /**
     * Writes text at the end of a file
     * Uses FileWriter and BufferedWriter to write
     * @param text String to add at the end of the file
     * @param file Path of the file to write
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public void write(String text, String fileIn) {
        try {
            FileWriter writer = new FileWriter(fileIn, true); // Append to the file, not overwrite it.
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            
            bufferWriter.write(text); // Write the text to the file
            bufferWriter.close();
        } 
        
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    /**
     * Copies a file byte by byte (for any file type)
     * Uses BufferedInputStream and BufferedOutputStream
     * @param readFile Path of the file to read
     * @param writeFile Path of the file to write
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public void copyByte(String readJPG, String writeFileJPG) {
        byte[] buffer = new byte[8192];
        int bytesRead = 0;
        try {
            FileInputStream fileInput = new FileInputStream(readJPG);
            BufferedInputStream in = new BufferedInputStream(fileInput);

            FileOutputStream fileOutput = new FileOutputStream(writeFileJPG);
            BufferedOutputStream out = new BufferedOutputStream(fileOutput);
            
            bytesRead = in.read(buffer);     
                   
            while (bytesRead != -1) {
                out.write(buffer, 0, bytesRead);
                bytesRead = in.read(buffer);
            }

            in.close();
            out.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } 
    }
    
} // Class end