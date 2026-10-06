package A011TeamMatch.Match;

/**
 * MatchTest.java
 * Test class for Team and Match classes
 * Tests all methods including playMatch, endMatch, showId, toString, getters and setters
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class MatchTest {
    
    public static void main(String[] args) {
        
        System.out.println("\n=== TESTING TEAM CLASS ===\n");

        //Create a variable to store the previous team name
        String previousTeamName = "";
        
        // Create Team objects
        System.out.println("--- Creating Teams ---");
        Team teamBarcelona = new Team("Barça");
        Team teamMadrid = new Team("Madrid");
        Team teamValencia = new Team("Valencia");
        Team teamSevilla = new Team("Sevilla");
        
        System.out.println("Teams created successfully!");
        System.out.println();
        
        // Test Team.toString()
        System.out.println("--- Testing Team.toString() ---");
        System.out.println(teamBarcelona.toString());
        System.out.println(teamMadrid.toString());
        System.out.println(teamValencia.toString());
        System.out.println(teamSevilla.toString());
        System.out.println();

        // Test Team.showId() - static method
        System.out.println("--- Testing Team.showId() ---");
        System.out.println("Next Team ID will be: " + Team.showId());
        System.out.println();
        
        // Test Team getters
        System.out.println("--- Testing Team Getters ---");
        System.out.println("Team 1 name: " + teamBarcelona.getNameTeam());
        System.out.println("Team 1 points: " + teamBarcelona.getPointsTeam());
        System.out.println("Team 2 name: " + teamMadrid.getNameTeam());
        System.out.println("Team 2 points: " + teamMadrid.getPointsTeam());
        System.out.println();
        
        // Test Team setters
        System.out.println("--- Testing Team Setters ---");
        System.out.println("Changing team names and points...");
        previousTeamName = teamValencia.getNameTeam();
        System.out.println("Non updated team: " + previousTeamName);
        teamValencia.setNameTeam("Valencia CF");
        teamValencia.setPointsTeam(5);
        System.out.println("Updated team: " + teamValencia.getNameTeam());
        System.out.println();
        
        // Test Team.incrementPoints()
        System.out.println("--- Testing Team.incrementPoints() ---");
        System.out.println("Before increment: " + teamSevilla.toString());
        teamSevilla.incrementPoints(3);
        System.out.println("After adding 3 points: " + teamSevilla.toString());
        teamSevilla.incrementPoints(1);
        System.out.println("After adding 1 point: " + teamSevilla.toString());
        System.out.println();
        
        System.out.println("\n=== TESTING MATCH CLASS ===\n");
        
        // Create Match objects
        System.out.println("--- Creating Matches ---");
        Match match1 = new Match(teamBarcelona, teamMadrid);
        Match match2 = new Match(teamValencia, teamSevilla);
        
        System.out.println("Matches created successfully!");
        System.out.println();

        // Test Match.toString() before playing
        System.out.println("-- Testing Match.toString() (before playing) ---");
        System.out.println(match1.toString());
        System.out.println(match2.toString());
        System.out.println();
  
        // Test Match.showId() - static method
        System.out.println("--- Testing Match.showId() ---");
        System.out.println("Next Match ID will be: " + Match.showId());
        System.out.println();
        
        // Test Match getters
        System.out.println("--- Testing Match Getters (before match) ---");
        System.out.println("Match 1 - Local Team: " + match1.getLocalTeam().getNameTeam());
        System.out.println("Match 1 - Visitant Team: " + match1.getVisitantTeam().getNameTeam());
        System.out.println("Match 1 - Goals Local: " + match1.getGoalsLocalTeam());
        System.out.println("Match 1 - Goals Visitant: " + match1.getGoalsVisitantTeam());
        System.out.println("Match 1 - ID: " + match1.getId());
        System.out.println();
        
        // Test Match.playMatch()
        System.out.println("--- Testing Match.playMatch() ---");
        System.out.println("\n>> Playing Match 1: " + teamBarcelona.getNameTeam() + " vs " + teamMadrid.getNameTeam());
        match1.playMatch(); // No parameters because they are already in the constructor
        System.out.println("After playing: " + match1.toString());
        System.out.println("Goals - Local: " + match1.getGoalsLocalTeam() + ", Visitant: " + match1.getGoalsVisitantTeam());
        System.out.println();
        
        System.out.println(">> Playing Match 2: " + teamValencia.getNameTeam() + " vs " + teamSevilla.getNameTeam());
        match2.playMatch();
        System.out.println("After playing: " + match2.toString());
        System.out.println("Goals - Local: " + match2.getGoalsLocalTeam() + ", Visitant: " + match2.getGoalsVisitantTeam());
        System.out.println();
        
        // Test Match.endMatch()
        System.out.println("--- Testing Match.endMatch() ---");
        System.out.println("\n>> Ending Match 1:");
        String resultMatch1 = match1.endMatch();
        System.out.println("Result: " + resultMatch1);
        System.out.println("After match: " + match1.toString());
        System.out.println();
        
        System.out.println(">> Ending Match 2:");
        String resultMatch2 = match2.endMatch();
        System.out.println("Result: " + resultMatch2);
        System.out.println("After match: " + match2.toString());
        System.out.println();
        
        // Test Match setters
        System.out.println("--- Testing Match Setters ---");
        System.out.println("Creating a new match and modifying it...");
        Team teamAtletico = new Team("Atlético");
        Team teamBilbao = new Team("Bilbao");
        Match match3 = new Match(teamAtletico, teamBilbao);
        
        System.out.println("Original match: " + match3.toString());
        
        // Change teams
        match3.setLocalTeam(teamBarcelona);
        match3.setVisitantTeam(teamMadrid);
        System.out.println("After changing teams: " + match3.toString());
        
        // Change goals manually
        match3.setGoalsLocalTeam(2);
        match3.setGoalsVisitantTeam(3);
        System.out.println("After setting goals manually: " + match3.toString());
        System.out.println();
        
        // Final summary
        System.out.println("\n=== FINAL SUMMARY ===\n");
        System.out.println("All Teams:");
        System.out.println(teamBarcelona.toString());
        System.out.println(teamMadrid.toString());
        System.out.println(teamValencia.toString());
        System.out.println(teamSevilla.toString());
        System.out.println(teamAtletico.toString());
        System.out.println(teamBilbao.toString());
        System.out.println();
        System.out.println("All Matches:");
        System.out.println(match1.toString());
        System.out.println(match2.toString());
        System.out.println(match3.toString());
        System.out.println();
        
        System.out.println("=== ALL TESTS COMPLETED SUCCESSFULLY ===");
    }
}