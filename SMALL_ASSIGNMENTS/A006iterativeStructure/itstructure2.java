package A006iterativeStructure;

import java.util.Scanner;

/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a Java code that creates an extended version of the rock paper scissors game
 * 
 * RULES:
 * Paper covers rock
 * Rock crushes lizard
 * Lizard poisons Spock
 * Spock smashes scissors
 * Scissors decapitates lizard
 * Lizard eats paper
 * Paper disproves Spock
 * Spock vaporizes rock
 * Rock crushes scissors.
 * 
 * 
 * STEP BY STEP:
 * 1. Create a Scanner object to read user input
 * 2. Start a loop to play the game 
 * 3. Get the user's and computer's choice
 * 4. Transform the byte selections into text
 * 5. Get the winner
 * 6. Update the score
 * 7. Print the score
 * 8. Ask the user if they want to play again, if "Yes" go back to step 2, if no go to step 9.
 * 9. Close the scanner and end the program.
 */

class ItStructure2 {

//================================================================================
// CONSTANTS
//================================================================================

    // These are the names of the hand shapes
    private static final String ROCK_NAME = "ROCK";
    private static final String PAPER_NAME = "PAPER";
    private static final String SCISSORS_NAME = "SCISSORS";
    private static final String LIZARD_NAME = "LIZARD";
    private static final String SPOCK_NAME = "SPOCK";

    // These are the corresponding byte values of the hand shapes
    private static final byte ROCK_VALUE = 1;
    private static final byte PAPER_VALUE = 2;
    private static final byte SCISSORS_VALUE = 3;
    private static final byte LIZARD_VALUE = 4;
    private static final byte SPOCK_VALUE = 5;  


//================================================================================
// MAIN METHOD
//================================================================================


    public static void main(String[] args) 
    {
        //Initial variables
        int playerPoints = 0; 
        int computerPoints = 0;  
        byte playAgainAnswer = 0; // 1 = Yes, 2 = No

        // Necessary objects
        ItStructure2 instance = new ItStructure2();
        Scanner userKeyboardInput = new Scanner(System.in);

        //GAME LOOP
        while (playAgainAnswer != 2) {

            // Get input phase
            System.out.println("\n1:ROCK 2:PAPER 3:SCISSORS 4:LIZARD 5:SPOCK");
            byte userChoiceInBytes = instance.getDelimitedByteInput("CHOOSE YOUR OPTION: ", userKeyboardInput, (byte) 1, (byte) 5);
            byte computerChoiceInBytes = instance.getRPSLS();
            
            // Transform into the text phase
            String userChoiceInText = instance.getText(userChoiceInBytes);
            String computerChoiceInText = instance.getText(computerChoiceInBytes);

            // Define the winner phase
            String winner = instance.winner(userChoiceInBytes, computerChoiceInBytes);

            // Update the score phase
            if (winner.equals("player1")) { // equals is recommended when comparing strings
                playerPoints++;
            } else if (winner.equals("player2")) {
                computerPoints++;
            }
            // DEBUG PRINTING
            System.out.println("\nPlayer 1: " + userChoiceInText);
            System.out.println("Player 2: " + computerChoiceInText);
            System.out.println("\nThe winner is: " + winner);

            // Print the score phases
            System.out.println("\nPoints player 1: " + playerPoints);
            System.out.println("Points player 2: " + computerPoints);

            // Ask the user if they want to play again
            playAgainAnswer = instance.getDelimitedByteInput("\nDo you want to play again? 1:YES 2:NO? ", userKeyboardInput, (byte) 1, (byte) 2);

            if (playAgainAnswer == 1) {
                System.out.println("**********************************************************");
            }

        } // END OF WHILE LOOP

        // Close the scanner
        userKeyboardInput.close();

        System.out.println("Bye!\n");
        
        
    } // END OF MAIN METHOD


//================================================================================
// METHODS
//================================================================================

    public byte getRPSLS() {
        return (byte) ((Math.random() * 5) + 1);
    }


    public byte getDelimitedByteInput(String message,Scanner userKeyboardInput, byte minimumValue, byte maximumValue) {
        byte userChoice;
        while (true) {
            System.out.print(message);
            userChoice = userKeyboardInput.nextByte();
            if (userChoice >= minimumValue && userChoice <= maximumValue) {
                return userChoice;
            }
        }
    }
    

    public String getText(byte playerNameChoice) {

        switch (playerNameChoice) {
            case ROCK_VALUE: return ROCK_NAME;
            case PAPER_VALUE: return PAPER_NAME;
            case SCISSORS_VALUE: return SCISSORS_NAME;
            case LIZARD_VALUE: return LIZARD_NAME;
            case SPOCK_VALUE: return SPOCK_NAME;
            default: return "INVALID CHOICE";
        }
    }

    // returns the name of the winner
    public String winner(byte userChoiceInBytes, byte computerChoiceInBytes) 
    {    
  
        String humanWinner = "player1";
        String computerWinner = "player2";
        String draw = "draw";

        if (userChoiceInBytes == computerChoiceInBytes) { // Any hand shape against itself is a draw
            return draw;
        }

        else if (userChoiceInBytes == ROCK_VALUE) { // Rock beats Scissors and Lizard
            if (computerChoiceInBytes == SCISSORS_VALUE || computerChoiceInBytes == LIZARD_VALUE) {
                return humanWinner;
            } 
            return computerWinner;
        }

        else if (userChoiceInBytes == PAPER_VALUE) { // Paper beats Rock and Spock
            if (computerChoiceInBytes == ROCK_VALUE || computerChoiceInBytes == SPOCK_VALUE) {
                return humanWinner;
            } 
            return computerWinner;
        }

        if (userChoiceInBytes == SCISSORS_VALUE) { // Scissors beats Paper and Lizard
            if (computerChoiceInBytes == PAPER_VALUE || computerChoiceInBytes == LIZARD_VALUE) {
                return humanWinner;
            } 
            return computerWinner;
        }

        else if (userChoiceInBytes == LIZARD_VALUE) { // Lizard beats Paper and Spock
            if (computerChoiceInBytes == PAPER_VALUE || computerChoiceInBytes == SPOCK_VALUE) {
                return humanWinner;
            } 
            return computerWinner;
        }

        else if (userChoiceInBytes == SPOCK_VALUE) { // Spock beats Rock and Scissors
            if (computerChoiceInBytes == ROCK_VALUE || computerChoiceInBytes == SCISSORS_VALUE) {
                return humanWinner;
            } 
            return computerWinner;
        }
        return "I DON'T KNOW HOW YOU GOT HERE! BUT YOU MADE AN INVALID CHOICE!";
    }

} // END OF MAIN CLASS ItStructure2
