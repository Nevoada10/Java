package tests;

import java.util.Locale;
import java.util.Scanner;

public class Descomposicio {

// ======================================================================================================================
// CONSTANTS
// ======================================================================================================================

	private static final String MENU_MESSAGE = "\nMenu\n" +
	"-----------------------\n" +
	"1. Mean value\n" +
	"2. Max value\n" +
	"3. Min value\n" +
	"4. Sum value\n" +
	"5. Most Frequent value\n" +
	"6. Percentage greater than mean\n" +
	"0. Quit \n" +
	"Select an option (0-6) ?\n" +
	"Enter option: ";

// ======================================================================================================================
// MAIN METHOD
// ======================================================================================================================

	public static void main(String[] args) {

	Scanner scanner = new Scanner(System.in);
	scanner.useLocale(Locale.UK);

	// Variables
	boolean isValid;
	isValid = false;
	int number = 0; // 
	int lengthArray = 0;

	// Enter array length (1...10)
	System.out.print("\nEnter length of array between 1 and 10\n");
	lengthArray = getArrayLength(isValid, lengthArray, scanner); 

	// Enter array data
	double[] dades = new double[lengthArray];
	boolean isDouble = false;
	dades =enterData(isValid, lengthArray, scanner, dades, isDouble);

	// Print menu 
	System.out.println(MENU_MESSAGE);

	// Enter option via scanner
	isValid = false;
	number = chooseOption(isValid, number, scanner);
	
	// Execute MENU chosen option
	if (number == 0) {
		System.out.println("Bye! ");
	}
	while (number != 0) {
		switch (number) {

		case 1:
			meanValue(dades);
			break;
		case 2:
			maxValue(dades);
			break;
		case 3:
			minValue(dades);
			break;
		case 4:
			sumValue(dades);
			break;
		case 5:
			mostFrequentValue(dades);
			break;
		case 6:
			percentageGreaterThanMean(dades);
			break;

		default:
			System.out.println("\nIncorrect option!!!\n");
		}

			System.out.print(MENU_MESSAGE);

		// Error handling
		errorHandling(scanner);
	}
	scanner.close();
}// end main

// ======================================================================================================================
// METHODS
// ======================================================================================================================


	/**
	 * This method takes a boolean and two int objects as parameters
	 * and returns an int object. It prints the String object to the console
	 * and then reads an int from the console and returns it as the result if it is
	 * within a given range.
	 * 
	 * @param isValid A boolean indicating if the input was valid.
	 * @param lengthArray The int object to be filled with the desired length.
	 * @param scanner The Scanner object to read from.
	 * @return The int object read from the console.
	 */
private static int getArrayLength(boolean isValid, int lengthArray, Scanner scanner) {

	while (!isValid || lengthArray < 1 || lengthArray > 10) {

		if (scanner.hasNextInt()) {
			lengthArray = scanner.nextInt();

			if (lengthArray > 0 && lengthArray < 11) {
				isValid = true;
			}
			
			else {
				System.out.println("Error! Enter opcion 1..10: ");
			}

		} else {
			scanner.next();
			System.out.println("Error! Enter opcion: ");
		}
	}
	return lengthArray;
}


/**
 * This method takes a boolean, an int, a Scanner object, a double array, and a boolean as parameters
 * and returns a double array. It asks the user to input the values of the array
 * and stores them in the array.
 * 
 * @param isValid A boolean indicating if the input was valid.
 * @param lengthArray The number of elements in the array.
 * @param scanner The Scanner object to read from.
 * @param dades The double array to be filled with user input.
 * @param isDouble A boolean indicating if the user input was a double.
 * @return The filled double array.
 */
private static double[] enterData(boolean isValid, int lengthArray, Scanner scanner, double[] dades, boolean isDouble) {

	for (int i = 0; i < dades.length; i++) {
		isDouble = false;
		System.out.println("Enter number 1.0 .. 10.0 [" + i + "]:");
		while (!isDouble) {
			if (scanner.hasNextDouble()) {
				dades[i] = scanner.nextDouble();
				
				if (dades[i] > 0 && dades[i] < 11) {
					isDouble = true;
					
				} else {
					System.out.println("Error! Enter valid number 1.0 ..10.0: ");
				}
				isDouble = true;
			} else {
				scanner.next();
				System.out.println("This is not a number. Enter number [" + i + "]:");
			}
		}
	}
	return dades;
}

/**
 * This method takes a boolean, an int, and a Scanner object as parameters
 * and returns an int. It asks the user to input a number between 0 and 6
 * and returns the input number after validation.
 * 
 * @param isValid A boolean indicating if the input was valid.
 * @param number The int number to be validated.
 * @param scanner The Scanner object to read from.
 * @return The validated int number.
 */
public static int chooseOption (boolean isValid, int number, Scanner scanner) {
	while (!isValid || number < 0 || number > 6) {
		if (scanner.hasNextInt()) {
			number = scanner.nextInt();
			if (number >= 0 && number < 7 ) {
				isValid = true;
			} 
			
			else {
				System.out.println("Error! Enter opcion: ");
			}
		} 
	
		else {
			scanner.next();
			System.out.println("Error! Enter opcion: ");
		}
	}
	return number;
}


/**
 * This method takes a double array as a parameter and prints the mean value of the array.
 * If the array is empty, it prints "No data found!".
 * Otherwise, it prints the data and then calculates and prints the mean value of the array.
 * 
 * @param dades The double array to calculate the mean value of.
 */
public static void meanValue(double[] dades) {

	System.out.println("\nMean value: ");

	if (dades.length == 0)
		// Array is empty
		System.out.println("No data found!");

	else {
		
		// Print array elements
		System.out.print("Data: ");
		for (double value : dades) {
			System.out.print(value + " ");
		}
		System.out.println("");

		// Mean calculation
		double sum = 0;
		for (int i = 0; i < dades.length; i++) {
			sum += dades[i];
		}
		
		// Print
		double mean = sum / dades.length;
		System.out.println("Mean value: " + mean);
		
	}
}


/**
 * This method takes a double array as a parameter and prints the maximum value of the array.
 * If the array is empty, it prints "No data found!".
 * Otherwise, it prints the data and then calculates and prints the maximum value of the array.
 * 
 * @param dades The double array to calculate the maximum value of.
 */
public static void maxValue(double[] dades) {

	System.out.println("\nMax value: ");

	if (dades.length == 0)
		// Array is empty
		System.out.println("No data found!");
	else {

		// Print array elements
		System.out.print("Data: ");
		for (double value : dades) {
			System.out.print(value + " ");
		}
				System.out.println("");

		// Max calculation
		double max = dades[0];
		for (int i = 1; i < dades.length; i++) {
			if (dades[i] > max)
				max = dades[i];
		}

		// Print
		System.out.println("Max value: " + max); 
		
	}
}

/**
 * This method takes a double array as a parameter and prints the minimum value of the array.
 * If the array is empty, it prints "No data found!".
 * Otherwise, it prints the data and then calculates and prints the minimum value of the array.
 * 
 * @param dades The double array to calculate the minimum value of.
 */
public static void minValue(double[] dades) {

	System.out.println("\nMin value: ");

	if (dades.length == 0)
		// Array is empty
		System.out.println("No data found!");

	else {

		// Print array elements
		System.out.print("Data: ");
		for (double value : dades) {
			System.out.print(value + " ");
		}
		System.out.println("");

		// Min calculation
		double min = dades[0];
		for (int i = 1; i < dades.length; i++) {
			if (dades[i] < min)
				min = dades[i];
		}

		// Print
		System.out.println("Min value: " + min);
	}

}


/**
 * This method takes a double array as a parameter and prints the sum value of the array.
 * If the array is empty, it prints "No data found!".
 * Otherwise, it prints the data and then calculates and prints the sum value of the array.
 * 
 * @param dades The double array to calculate the sum value of.
 */
public static void sumValue(double[] dades) {
	System.out.println("\nSum value: ");

	if (dades.length == 0) {
		// Array is empty
		System.out.println("No data found!");
	}
	else {
		// Print array elements
		System.out.print("Data: ");
		for (double value : dades) {
			System.out.print(value + " ");
		}
		System.out.println("");
		double sum = 0;

		// Calculate sum
		for (int i = 0; i < dades.length; i++) {
			sum += dades[i];
		}

		// Print
		System.out.println("Sum value: " + sum);
	}
}

public static void mostFrequentValue(double[] dades) {

	System.out.println("\nMost frequent value: ");

	// Variables
	byte contador = 0; // keeps track of the number of occurrences of an index
	double numeroFinal = 0; // Here we will store the position of the most frequent number (finalNumber)
	byte max = 0; // This variable keeps track of the maximum number of occurrences

	if (dades.length == 0)
	// Array is empty
		System.out.println("No data found!");


	else {
		// Print array elements
		System.out.print("Data: ");
		for (double value : dades) {
			System.out.print(value + " ");
		}
		System.out.println("");


		// Main logic
		for (int i = 0; i < dades.length; i++) {
			contador = 0;
			for (int j = 0; j < dades.length; j++) {
				if (i == j) {
					continue; // saltem i sortim del bucle for (j)
				}
				if (dades[i] == dades[j]) {
					contador++;
					if (contador > max) {
						max = contador;
						numeroFinal = dades[i];
					}
				}
			}
		}

		// Print
		System.out.println("String value: " + numeroFinal);
	}

}

/**
 * This method takes a double array as a parameter and prints the percentage of elements in the array that are greater than the mean.
 * If the array is empty, it prints "No data found!".
 * Otherwise, it prints the data, calculates the mean, and then calculates and prints the percentage of elements in the array that are greater than the mean.
 * 
 * @param dades The double array to calculate the percentage of elements greater than the mean of.
 */
public static void percentageGreaterThanMean(double[] dades) {
System.out.println("\nPercentage greater than mean: ");

	if (dades.length == 0)
		// Array is empty
		System.out.println("No data found!");

	else {
		// Print array elements
		System.out.print("Data: ");
		for (double value : dades) {
			System.out.print(value + " ");
		}
		System.out.println("");

			//Calculate sum
			double sum = 0;
			for (int i = 0; i < dades.length; i++) {
				sum += dades[i];
			}

			//Calculate mean
			double mean = sum / dades.length;	
			//2 decimals
			double factor = Math.pow(10.0, 2);
			double meanRounded = Math.round(mean * factor) / factor; // Round it
			
			System.out.println("Mean: " + meanRounded);

			//Find percentage greater than mean
			int count = 0;
			for (int i = 0; i < dades.length; i++) {
				if(dades[i] > mean)
					count++;
			}
			
			double percentage = count / (double)dades.length * 100;				
			//2 decimals
			double factor2 = Math.pow(10.0, 2);
			double percentageRounded = Math.round(percentage * factor2) / factor2;
			
			System.out.println("Percentage greater than mean: " +  percentageRounded + "%");
	}
}


	/**
	 * This method is used to handle errors when the user enters an invalid option.
	 * It will keep asking the user for input until a valid option is entered.
	 * 
	 * @param isValid A boolean that indicates whether the input is valid or not.
	 * @param number The number entered by the user.
	 * @param scanner A scanner object used to read user input.
	 */
	public static void errorHandling(Scanner scanner) {
		boolean isValid = false;
		int number = 0;
		// Keep asking the user for input until a valid option is entered.
		while (!isValid || number < 0 || number > 6) {
			if (scanner.hasNextInt()) {
				number = scanner.nextInt();
				
				if (number >= 0 && number < 7) {
					isValid = true;
				} else {
					// If the number is not within the valid range, print an error message and set isValid to false.
					System.out.println("Error, not in range! Enter option: ");
					isValid = false;
				}
			
			} else {
				// If the input is not a valid integer, print an error message and discard the input.
				scanner.next();
				System.out.println("Error, not a valid integer! Enter option: ");
				isValid = false;
			}
		}
	}


//=====================================================================================================================
} // CLASS end
//=====================================================================================================================
// END

