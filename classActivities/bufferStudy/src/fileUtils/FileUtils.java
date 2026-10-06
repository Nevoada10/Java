package fileUtils;

import java.io.*;

public class FileUtils{

	/**
	 * Reads line by line a file and prints its content
	 * @param fileIn path of the file to read
	 * @return void, print the file
	 */
        @SuppressWarnings("ConvertToTryWithResources")
	public void readFile(String fileIn) {

	// Read line by line the file
	try {
		FileReader content = new FileReader(fileIn);
		BufferedReader reader = new BufferedReader(content);
		String line = reader.readLine();

		// While the line is not empty, print it
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
		}
		System.out.println();
		reader.close();
	}

	catch (FileNotFoundException e) {
		System.out.println("File not found" + e.getMessage());
	} 
	catch (IOException e) {
		System.out.println("Error reading file" + e.getMessage());
	}
}

/**
 * @param fileIn: path of the file to read
 * @return int number of lines
 */
@SuppressWarnings("ConvertToTryWithResources")
public int fileNumLinies(String fileIn) {
    int contador = 0;
    
    // Read line by line the file
    try {
        FileReader content = new FileReader(fileIn);
        BufferedReader reader = new BufferedReader(content);
        String line = reader.readLine();
        
        // While the line is not empty, count it
        while (line != null) {
            contador++;
            line = reader.readLine();
        }
        System.out.println("Exercise 2: \nNumber of lines = " + contador + "\n");
        reader.close();
    }
    catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    } 
    catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
    
    return contador;
}

	/**
	 * indexOf returns an int value, representing the index of the first occurrence of the character in the string, or -1 if it never occurs
	 * @param searchStringPattern: string of pattern to search
	 * @param fileToRead: path of the file to read
	 * @return int number of line that contains the pattern, or 0 if not found
	 */
	@SuppressWarnings({"ConvertToTryWithResources", "IndexOfReplaceableByContains"})
	public int fileContainsPattern(String searchStringPattern, String fileToRead) {
		int lineCounter = 0;
		
		try {
			FileReader fileContent = new FileReader(fileToRead); 
			BufferedReader fileReader = new BufferedReader(fileContent); 
			String fileLine = fileReader.readLine(); 
			System.out.println("Exercise 3:");
			
			// While the line is not empty, search for the pattern
			while (fileLine != null) {
				lineCounter++;

				// If pattern is not found 
				if (fileLine.indexOf(searchStringPattern) == -1) {
					fileLine = fileReader.readLine();
					System.out.println("Did not find the pattern '" + searchStringPattern + "' in line " + lineCounter);
					continue; // Go to the beginning of the loop
				}
				
				// If pattern is found
				System.out.print("Found " + searchStringPattern + " in line " + lineCounter + " ");
				fileReader.close();
				return lineCounter;
			}
			
			fileReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} 
		catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		
		return 0;
	}
		
      /**
        * @param fileIn: path of the file to read
		* @param fileOut: path to file to write
        * @return int number of lines
		* 
		* Read the file line by line
		* Copy each line to the destination file
		* returns the number of lines processed
        */
        @SuppressWarnings("ConvertToTryWithResources")
        public int fileDuplica(String fileIn, String fileOut) {
			int contador = 0;
			try {
				// Create buffered reader line
				FileReader content = new FileReader(fileIn);
				BufferedReader reader = new BufferedReader(content);
				String line = reader.readLine();

				// Create buffered writer
				FileWriter file = new FileWriter(fileOut);
				BufferedWriter writer = new BufferedWriter(file);

				// While the line is not empty, write it
				System.out.println("\n\nExercise 4:\nCopying contents of file " + fileIn + " to file " + fileOut);
				while (line != null) {
					contador++;
					writer.write(line);
					writer.newLine();
					line = reader.readLine();
				}

				// Close both writer and reader
				writer.close();
				reader.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found: " + e.getMessage());
			} 
			catch (IOException e) {
				System.out.println("Error reading file: " + e.getMessage());
			}
			return contador;

		}


		  /**
		* @param searchPattern: string of pattern to search
        * @param fileToRead: path of the file to read
        * @param fileToWrite: path to file to write
        * @return int number of lines
		* 
		* Read the file line a line
		* for each line that contains the search pattern
		* write the line to the output file
		* returns the total number of occurrences (of lines) of the pattern
        */
        @SuppressWarnings({"ConvertToTryWithResources", "IndexOfReplaceableByContains"})
        public int copyLinesWithPatternToFile(String searchPattern, String fileToRead, String fileToWrite) {
			int lineCounter = 0;

			try {
				FileReader fileContent = new FileReader(fileToRead);
					
				// Create buffered reader line
				BufferedReader fileReader = new BufferedReader(fileContent);
				String fileLine = fileReader.readLine();

				// Create buffered writer
				FileWriter outputFile = new FileWriter(fileToWrite);
				BufferedWriter writerToFile = new BufferedWriter(outputFile);

				// While the line is not empty, look for the pattern, if you find it add 1 to the counter
				System.out.println("\nExercise 5:\nCopying contents of file " + fileToRead + " to file " + fileToWrite);
				while (fileLine != null) {
					if (fileLine.indexOf(searchPattern) != -1) {
						lineCounter++;
						writerToFile.write(fileLine);
						writerToFile.newLine();
					}
					fileLine = fileReader.readLine();
				}

				// Close both writer and reader
				writerToFile.close();
				fileReader.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found: " + e.getMessage());
			} 
			catch (IOException e) {
				System.out.println("Error reading file: " + e.getMessage());
			}
			System.out.println("Found " + searchPattern + " in " + lineCounter + " lines");
			return lineCounter;

		}
	
	

}