package Exam1;

import java.util.Scanner;

public class temperature {
// ==========================================================================
// CONSTANTS
// ==========================================================================

    private static final byte QUIT_VALUE = 0;
    private static final byte RANGE_VALUE = 1;
    private static final byte CONVERT_TO_VALUE = 2;
    private static final byte AVG_TEMP_VALUE = 3;
    private static final byte MAX_DIF_DAY_VALUE = 4;
    private static final byte CONVERT_FAHRENHEITS_VALUE = 5;  
    private static final byte TOF_VALUE = 6;

    public static final double[] temps = { 6.02, 5.17, 3.61, 9.54, 6.6, 0.34, 3.77, 6.28, 1.63, 3.07, 5.58, 3.42, 2.34, 6.58, 4.12,
        0.11, 7.29, 9.13, 7.21, 7.71, 4.04, 0.35, 0.38, 9.52, 1.62, 9.4, 0.39, 8.27, 0.64, 9.15, 0.06, 2.08,
        6.88, 3.85, 1.76, 5.25, 6.62, 7.79, 9.0, 5.76, 6.3, 6.64, 5.61, 7.42, 7.3, 3.07, 9.89, 1.05, 6.75, 3.86,
        6.55, 5.66, 8.63, 5.85, 2.89, 7.46, 5.79, 4.2, 6.9, 2.0, 11.91, 17.5, 13.94, 12.17, 14.75, 10.9, 15.67,
        12.82, 16.18, 19.99, 19.47, 10.77, 11.85, 15.59, 19.21, 17.76, 13.81, 11.74, 18.48, 12.61, 12.04, 17.17,
        15.54, 17.84, 16.87, 13.45, 15.3, 15.89, 12.69, 11.36, 17.13, 19.31, 14.96, 10.91, 19.22, 19.93, 16.61,
        19.14, 19.39, 11.91, 17.02, 14.3, 19.32, 14.72, 16.62, 14.77, 14.62, 12.82, 12.77, 15.64, 15.48, 10.25,
        18.21, 16.48, 13.55, 10.14, 11.41, 19.13, 19.19, 10.71, 19.45, 15.47, 19.26, 12.18, 18.02, 10.96, 19.95,
        11.03, 19.58, 11.57, 10.76, 11.33, 10.57, 18.32, 15.83, 15.9, 10.72, 15.93, 15.8, 13.74, 12.77, 19.24,
        19.22, 13.67, 18.87, 14.2, 15.33, 18.78, 10.66, 17.9, 26.65, 25.41, 30.54, 33.55, 31.26, 31.53, 30.04,
        28.31, 39.9, 26.13, 37.64, 35.06, 32.85, 35.53, 26.12, 29.57, 30.45, 37.36, 31.21, 37.99, 35.4, 37.11,
        30.13, 35.75, 33.31, 29.7, 32.32, 31.59, 34.16, 39.32, 29.23, 37.21, 36.85, 36.12, 29.35, 36.93, 26.63,
        34.31, 27.87, 28.29, 38.26, 27.44, 33.16, 34.57, 28.29, 28.81, 38.75, 37.71, 26.99, 31.9, 35.91, 30.83,
        39.04, 33.81, 26.88, 33.28, 32.58, 37.72, 33.35, 27.65, 38.73, 39.28, 28.56, 37.76, 32.06, 30.98, 34.23,
        39.43, 33.76, 28.15, 25.61, 30.98, 26.02, 30.4, 29.31, 34.14, 30.73, 28.05, 29.32, 27.63, 25.96, 28.88,
        30.98, 39.63, 26.61, 37.43, 37.12, 33.37, 29.39, 25.69, 12.36, 13.29, 15.88, 11.28, 19.03, 17.3, 15.27,
        17.91, 18.49, 16.73, 11.56, 10.25, 10.94, 11.14, 14.85, 16.74, 17.9, 17.81, 16.41, 15.73, 14.66, 17.82,
        18.08, 12.83, 17.17, 10.11, 18.65, 18.66, 11.84, 10.09, 11.16, 14.71, 15.02, 12.26, 18.43, 19.68, 19.28,
        14.36, 14.9, 12.72, 17.66, 13.69, 11.89, 15.05, 13.15, 15.28, 19.66, 16.35, 10.01, 15.29, 14.23, 17.89,
        15.71, 12.54, 19.07, 19.78, 16.8, 15.46, 11.73, 16.16, 10.02, 12.0, 13.37, 18.14, 10.27, 14.87, 14.77,
        11.03, 10.35, 15.47, 15.81, 19.59, 13.5, 15.45, 16.71, 11.95, 18.81, 16.02, 18.33, 10.66, 10.97, 11.91,
        15.83, 17.3, 10.9, 18.3, 14.64, 14.97, 10.48, 16.38, 9.99, 1.87, 5.46, 5.76, 2.03, 7.84, 3.02, 9.27,
        6.25, 4.85, 6.71, 9.21, 5.09, 1.89, 9.68, 2.2, 0.34, 4.8, 8.94, 3.55, 7.55, 1.0, 7.26, 1.01, 2.91, 0.27,
        6.55, 2.2, 0.52, 2.85, 16.54, 19.48, 12.57, 17.36 };

    //Temps length
    private static final int tempsLength = temps.length;

    private static final String MENU = " \n MENU \n 1. RANGE \n 2. CONVERT TO F, K and R \n 3. AVERAGE TEMPERATURE \n 4. MAX DIFFERENCE DAY \n 5. CONVERT TO FAHRENHEITS \n 6. TOF \n 0. QUIT \n CHOOSE MENU OPTION: ";


// ==========================================================================
// MAIN CODE
// ==========================================================================



    public static void main(String[] args) {
        
        //Instantiate the scanner
        Scanner userInput = new Scanner(System.in);
        byte userChoice = 7;
        
        //Sort the array
        //Arrays.sort(temps);

        // Print menu
        while (userChoice != QUIT_VALUE) {
            
             // MENU
            userChoice = constrainedByteInput(MENU, userInput, QUIT_VALUE, TOF_VALUE);

            if (userChoice == QUIT_VALUE)
                {
                    System.out.println("quit");

                    break;
                }
            
            else if (userChoice == RANGE_VALUE) { // 1
                System.out.println("range");
                range(userInput, temps);
                

            }

            else if (userChoice == CONVERT_TO_VALUE) { // 2
                System.out.println("convert to");
                convertToFKR(userInput);
                break;
            }

            else if (userChoice == AVG_TEMP_VALUE) { // 3
                System.out.println("avg temp");
                annualAverage(temps, tempsLength);
                break;
            }

            else if (userChoice == MAX_DIF_DAY_VALUE) { // 4
                System.out.println("max dif day");
                maxDifference(temps);

                break;
            }

            else if (userChoice == CONVERT_FAHRENHEITS_VALUE) { 
                System.out.println("convert to fahrenheit");
            }

            else if (userChoice == TOF_VALUE) { // 6
                System.out.println("tof");
                ToF(temps);

                break;
            }



    } // While end

    userInput.close();


    } // MAIN END


// ==========================================================================
// METHODS
// ==========================================================================




public static byte constrainedByteInput(String message, Scanner textInput, byte minimumValue, byte maximumValue) {
    byte userChoice;
    // Create a loop to get constrained & persistent user input
        while (true) {
            System.out.print(message);
            userChoice = textInput.nextByte();
            if (userChoice >= minimumValue && userChoice <= maximumValue) {
                System.out.println("");
                return userChoice;
            }
        } 
    }

public static double[] range(Scanner userInput, double[] temps) {

    try 
    {
        System.out.print("Enter from: ");
        byte from = userInput.nextByte();
        System.out.print("Enter to: ");
        byte to = userInput.nextByte();        

        for ( int i = from; i < to; i++)
        {
            System.out.println(temps[i]);   
        }
        return temps;
    }
    //Index out of bounds exception
    catch (IndexOutOfBoundsException e)
    {
        System.out.println("Index out of bounds");
        return temps;
    }
}


public static double[] convertToFKR(Scanner userInput) {

    System.out.print("ENTER TEMPERATURE: ");
    double celsiusTemperature = userInput.nextDouble();

    double[] array = new double[3];

    double fahrenheit = celsiusTemperature * 9/5 + 32;
    double kelvin = celsiusTemperature + 273.15;
    double rankine = celsiusTemperature * 9/5 + 491.67;

    for (int i =0; i < 3; i++){
        array[0] = fahrenheit;
        array[1] = kelvin;
        array[2] = rankine;
    }

    for (int i = 0; i<3; i++){
        System.out.printf("%.2f\n" , array[i]);
    }

    return array;
}



public static double annualAverage(double[] temps, int tempsLength){

    double sum = 0;

    for ( int i = 0; i < tempsLength ; i++){
        sum += temps[i];
    }

    double average = sum / tempsLength;
    System.out.printf("%.2f\n", average);

    return sum;
    }


public static int maxDifference (double[] temps){

    int maxDifference = 0;
    int day = 1;

    System.out.println(16.54 -2.85);
    

    //compare adjacent elements and store the day (i) with the biggest variation
    for (int i = 0; i < temps.length - 1; i++) {
        double currentday = temps[i];
        double nextday = temps[i + 1];
        double difference = Math.abs(currentday - nextday);
        if (difference > maxDifference) {
            maxDifference = (int) difference;
            day = i + 1;
        }
    }

    System.out.println(day);
    return day;

}




public static double[] ToF(double [] temps){
    //For each element on the array temps, return a new array containing the temperature in fahrenheit
    double[] fahrenheitTemps = new double[temps.length];
    for (int i = 0; i < temps.length; i++) {
        fahrenheitTemps[i] = (temps[i] * 9/5) + 32;
    }

    // print the new array
    for (int i = 0; i < fahrenheitTemps.length; i++) {
        // print with 2 decimal places
        System.out.printf("%.2f\n", fahrenheitTemps[i]);
        
    }
    return fahrenheitTemps;
}


} // CLASS END

