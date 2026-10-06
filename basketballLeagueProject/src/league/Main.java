package league;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Main.java
 * Test class for Team, Match and League classes
 * Tests all methods and functionality of the basketball league system
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Main {


// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// MAIN (BASIC MENU)
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static void main(String[] args) {

    // Create scanner 
    Scanner input = new Scanner(System.in);

    try {
        // Ask user if they want to run the tests
        System.out.print("\n► Do you want to run the tests? (1 - Yes, 0 - No): ");
        byte answer = input.nextByte();

        // If and else if statement to handle user answer
        if (answer == 0) {
            // User wants to exit
            System.out.println("► You have exited the program, Goodbye! :(\n");
            System.exit(0);
        } else if (answer == 1) {
            // User wants to run the tests
            test();
        
        } else {
            // User has entered an invalid answer
            System.out.println("► You typed a byte, however it's not a 0 or 1.\n");
        }
    }

    // Catch mismatch output exception
    catch (InputMismatchException e) {
        System.out.println("► Error: Invalid type or range of input. Please try again. \nError message: " + e.getMessage() + "\n");
    }

    // Catch any other exception
    catch (Exception e) {
        System.out.println("► Error: Something went wrong bb. Please try again. \nError message: " + e.getMessage() + "\n");
    }
    
    // Close scanner
    finally {
        input.close();
    }
    
} // End of main method


//==============================================================================
// METHOD
//==============================================================================

    /**
     * Create a method containing the tests
     * @return 
     */
    public static void test() {
        //==============================================================================
        // TEAM TESTS
        //==============================================================================

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      TEAM TESTS                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        // Create 5 teams with descriptive names
        System.out.println("► Creating 5 teams...\n");
        Team ferrol = new Team(CodeTeam.FERROL);
        Team laseu = new Team(CodeTeam.LASEU);
        Team zaragoza = new Team(CodeTeam.ZARAGOZA);
        Team bosco = new Team(CodeTeam.BOSCO);
        Team ensino = new Team(CodeTeam.ENSINO);
        
        // Show all data from 
        System.out.println("► Showing all data from teams:");
        System.out.println(ferrol.toString());
        System.out.println(laseu.toString());
        System.out.println(zaragoza.toString());
        System.out.println(bosco.toString());
        System.out.println(ensino.toString());
        System.out.println();
        
        // Show code, name and city from one team
        System.out.println("► Showing code, name and city from Ferrol:");
        System.out.println("  Code: " + ferrol.getCodeTeam());
        System.out.println("  Name: " + ferrol.getCodeTeam().getName());
        System.out.println("  City: " + ferrol.getCodeTeam().getCity());
        System.out.println();
        
        // Increment team points by 3
        System.out.println("► Incrementing Ferrol points by 3...");
        ferrol.addPointsTeam(3);
        System.out.println("  Points after: " + ferrol.getPointsTeam());
        System.out.println();
        
        // Increment points for by 55
        System.out.println("► Incrementing Ferrol points for by 55...");
        ferrol.addPointsFor(55);
        System.out.println("  Points for after: " + ferrol.getPointsFor());
        System.out.println();
        
        // Increment points against by 77
        System.out.println("► Incrementing Ferrol points against by 77...");
        ferrol.addPointsAgainst(77);
        System.out.println("  Points against after: " + ferrol.getPointsAgainst());
        System.out.println();
        
        // Increment games played by 1
        System.out.println("► Incrementing Ferrol games played by 1...");
        ferrol.increaseGamesPlayed(1);
        System.out.println("  Games played after: " + ferrol.getGamesPlayed());
        System.out.println();
        
        // Increment games won by 1
        System.out.println("► Incrementing Ferrol games won by 1...");
        ferrol.increaseGamesWon(1);
        System.out.println("  Games won after: " + ferrol.getGamesWon());
        System.out.println();
        
        // Increment games lost by 1
        System.out.println("► Incrementing Ferrol games lost by 1...");
        ferrol.increaseGamesLost(1);
        System.out.println("  Games lost after: " + ferrol.getGamesLost());
        System.out.println();
        
        // Increment games tied by 1
        System.out.println("► Incrementing Ferrol games tied by 1...");
        ferrol.increaseGamesTied(1);
        System.out.println("  Games tied after: " + ferrol.getGamesTied());
        System.out.println();
        
        
        //==============================================================================
        // MATCH TESTS
        //==============================================================================

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      MATCH TESTS                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        // Reset Ferrol stats for clean match testing
        // (they have modified stats from previous tests)
        ferrol.setPointsTeam(0);
        ferrol.setPointsFor(0);
        ferrol.setPointsAgainst(0);
        ferrol.setGamesPlayed(0);
        ferrol.setGamesWon(0);
        ferrol.setGamesLost(0);
        ferrol.setGamesTied(0);
        
        // Create 1 match between 2 teams
        System.out.println("► Creating a match between " + ferrol.getCodeTeam() + " and " + laseu.getCodeTeam() + "...\n");
        Match match1 = new Match(ferrol, laseu);
        
        // Show match data before playing
        System.out.println("► Showing match data BEFORE playing:");
        System.out.println("  " + match1.toString());
        System.out.println();
        
        // Play the match
        System.out.println("► Playing the match...");
        match1.playMatch();
        System.out.println("  Match played! Points assigned randomly.");
        System.out.println();
        
        // End the match
        System.out.println("► Ending the match and updating team stats...");
        String matchResult = match1.endMatch();
        System.out.println();
        
        // Show match data after ending
        System.out.println("► Showing match data AFTER ending:");
        System.out.println("  " + match1.toString());
        System.out.println();
        
        // Show teams data after match ended
        System.out.println("► Showing teams data AFTER match ended:");
        System.out.println(matchResult);
        
        // Simulate a match with a tie (using Zaragoza and Bosco)
        System.out.println("► Simulating a match with a TIE...");
        Match match2 = new Match(zaragoza, bosco);
        
        // Force a tie by setting same points
        match2.setPointsLocalTeam((byte) 75);
        match2.setPointsVisitantTeam((byte) 75);
        
        System.out.println("  Forced tie: both teams scored 75 points");
        String tieResult = match2.endMatch();
        System.out.println("\n► Showing teams data AFTER tied match:");
        System.out.println(tieResult);
        
        
        //==============================================================================
        // LEAGUE TESTS
        //==============================================================================

        
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      LEAGUE TESTS                            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        // Create fresh teams for league (to start with clean stats)
        System.out.println("► Creating fresh teams for the league...\n");
        Team ferrolLeague = new Team(CodeTeam.FERROL);
        Team laseuLeague = new Team(CodeTeam.LASEU);
        Team zaragozaLeague = new Team(CodeTeam.ZARAGOZA);
        Team boscoLeague = new Team(CodeTeam.BOSCO);
        Team ensinoLeague = new Team(CodeTeam.ENSINO);
        
        // Create array of teams (minimum 5)
        Team[] leagueTeams = {
            ferrolLeague,
            laseuLeague,
            zaragozaLeague,
            boscoLeague,
            ensinoLeague
        };
        
        // Create league (creates all matches automatically)
        System.out.println("► Creating league (this generates all matches automatically)...");
        League league = new League(leagueTeams);
        System.out.println("  League created with " + league.getMatches().length + " matches!");
        System.out.println("  Formula: " + leagueTeams.length + " * (" + leagueTeams.length + " - 1) = " + league.getMatches().length + " matches\n");
        
        // Play the entire league
        System.out.println("► Playing the entire league (all matches)...");
        league.playLeague();
        System.out.println("  All matches played and finished!\n");
        
        // Show all matches
        System.out.println("► Showing all matches (format 1):");
        System.out.println(league.showMatches());
        
        System.out.println("\n► Showing all matches (format 2 - table):");
        System.out.println(league.showMatches2());
        
        // Show all teams
        System.out.println("\n► Showing all teams with statistics:");
        System.out.println(league.showTeams());
        
        // Show classification sorted
        System.out.println("\n► Showing SORTED classification:");
        Team[] classification = league.showClassification();
        
        System.out.printf("%-5s %-15s %5s %5s %5s %5s %5s %5s %5s\n",
                "ID", "CODE", "P", "PF", "PA", "GP", "GW", "GL", "GT");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < classification.length; i++) {
            System.out.println(classification[i]);
        }
        
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   ALL TESTS COMPLETED!                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");   
        System.out.println();
    }

} // End Class Main
// END