package fileUtils;

public class FileMain{
	/**
 	 * @param args
	 */
	public static void main(String[] args){
		FileUtils fu = new FileUtils(); 
		String fileIn = "./data/text.txt";
		String per = "URL";
		String fileOut = "./data/text.out";

		// From the files Utils.java class, use the fileLlegir(fileIn)
		System.out.println("\nExercise 1: Reading file " + fileIn);
		fu.readFile(fileIn);

		// Count the number of lines
		fu.fileNumLinies(fileIn);

		// Filter per lines containing the pattern
		fu.fileContainsPattern(per, fileIn); 

		// Copy the file
		fu.fileDuplica(fileIn, fileOut);
		
		// Copy the lines that contain the pattern
		fu.copyLinesWithPatternToFile(per, fileOut, fileOut);

	}
}