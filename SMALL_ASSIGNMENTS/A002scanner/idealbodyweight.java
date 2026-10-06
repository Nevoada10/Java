package A002scanner;

import java.util.Scanner;   

/**
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
* This is a Java code that gets userInput from the user and uses the Lorentz formula to calculate the ideal body weight.
*/

class IdealBodyWeight {

//======================================================================================================================
// CONSTANTS
//======================================================================================================================;

    private static final float K_FEMALE = 2.5f;
    private static final float K_MALE = 4.0f;

    public static void main(String[] args) {

//======================================================================================================================
// MAIN CODE
//======================================================================================================================

        // Declaring variables to get input
        short userHeightInCm;
        byte userAgeInYears;
        byte userGender;

        // Get text input from the user
        Scanner userInput = new Scanner(System.in);
        System.out.print("\nEnter your height in cm: ");
        userHeightInCm = userInput.nextShort();  // Height
        System.out.print("Enter your age: ");
        userAgeInYears = userInput.nextByte();  // Age
        System.out.print("Enter your gender (1 for man and 2 for woman): ");
        userGender = userInput.nextByte(); // Gender

        // Close the scanner 
        userInput.close();
        
// ======================================================================================================================
// OUTPUTS
//=======================================================================================================================
        
        // Calculate the ideal body weight using the Lorentz formula and print the result
        if (userGender == 1) { // Gender is male, k = 4
            float idealBodyWeightForMen = (userHeightInCm - 100) - ((float)(userHeightInCm - 150) / 4) + ((userAgeInYears - 20)/K_MALE);
            System.out.printf("\nIdeal Body Weight for Men = %.2fkg\n\n", idealBodyWeightForMen); // Print
        } 

        if (userGender == 2) { // Gender is female, k = 2.5
            float idealBodyWeightForWomen = (userHeightInCm - 100) - ((float)(userHeightInCm - 150) / 4) + ((userAgeInYears - 20)/K_FEMALE);
            System.out.printf("\nIdeal Body Weight for Women = %.2fkg\n\n", idealBodyWeightForWomen); // Print
        }                   
    }
}
// END