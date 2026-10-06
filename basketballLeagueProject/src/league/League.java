package league;

/**
 * League.java
 * Class representing a basketball league
 * Manages teams and matches in the league
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class League {

// ============================================
// ATTRIBUTES
// ============================================

    private Team[] teams;
    private Match[] matches;

// ============================================
// CONSTRUCTOR
// ============================================

    /**
     * Constructor
     * Creates all matches for the league
     * Each team plays against all other teams (round-robin tournament)
     * Formula: n * (n - 1) matches, where n = number of teams
     * Example: 5 teams → 5 * 4 = 20 matches
     * @param teams Array of teams in the league
     */
    public League(Team[] teams) {
        // Start by taking the teams array and storing it in our teams attribute
        this.teams = teams;
        
        // Now we need to calculate how many matches are needed in total.
        int numMatches = teams.length * (teams.length - 1);
        
        // Now we need to create an array to store all the matches. 
        // Create a NEW empty array that can hold exactly numMatches number of Match objects
        // Take that new array we just created, and put it inside the matches attribute of this League object
        this.matches = new Match[numMatches];
        
        // Now we need to generate all the possible matches between
        // the teams. We'll do this by looping through each team and
        // creating a match against every other team.
        int matchIndex = 0;
        for (int i = 0; i < teams.length; i++) { 
            // Loop through each team
            for (int j = 0; j < teams.length; j++) {
                // If the teams are different, create a match between them
                if (i != j) {  
                    matches[matchIndex] = new Match(teams[i], teams[j]);
                    matchIndex++;  // Move to next match slot
                }
            }
        }
    }

// ============================================
// GETTERS AND SETTERS
// ============================================

    /**
     * getTeam
     * Gets a team by index
     * @param index Index of the team in the array
     * @return Team at the specified index
     */
    public Team getTeam(int index) {
        return teams[index];
    }

    /**
     * setTeams
     * Sets the teams array
     * @param teams Array of teams
     */
    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    /**
     * getMatches
     * Gets all matches in the league
     * @return Array of all matches
     */
    public Match[] getMatches() {
        return matches;
    }

    /**
     * setMatches
     * Sets the matches array
     * @param matches Array of matches
     */
    public void setMatches(Match[] matches) {
        this.matches = matches;
    }

// ============================================
// METHODS
// ============================================

    /**
     * playLeague
     * Simulates all matches in the league
     * Loops through all matches and plays each one (playMatch + endMatch)
     */
    public void playLeague() {
        // Loop through all matches in the league
        for (int i = 0; i < matches.length; i++) {
            matches[i].playMatch();  // Simulate the match (assign random points)
            matches[i].endMatch();   // Process results and update team stats
        }
    }

   /**
     * toString
     * Returns a string representation of the league
     * Shows general league information: number of teams and matches
     * @return Formatted string with league information
     */
   @Override
   public String toString() {
       return "League{" +
              "teams=" + teams.length +
              ", matches=" + matches.length +
              '}';
   }

    /**
     * showTeams
     * Shows all teams in the league with their statistics
     * Format: ID CODE P PF PA GP GW GL GT
     * @return Formatted string with all teams
     */
    public String showTeams() {
        // Build the header
        String result = String.format("%-5s %-15s %5s %5s %5s %5s %5s %5s %5s\n",
                "ID", "CODE", "P", "PF", "PA", "GP", "GW", "GL", "GT");
        result += "----------------------------------------------------------------\n";
        
        // Loop through all teams and add each one
        for (int i = 0; i < teams.length; i++) {
            result += teams[i].toString() + "\n";
        }
        
        return result;
    }

    /**
     * showMatches
     * Shows all matches in the league with format:
     * Match ID: LocalTeam Points - VisitantTeam Points
     * @return Formatted string with all matches
     */
    public String showMatches() {
        // Build the header
        String result = "Match ID: LocalTeam Points - VisitantTeam Points\n";
        result += "--------------------------------------------------\n";
        
        // Loop through all matches and add each one
        for (int i = 0; i < matches.length; i++) {
            Match match = matches[i];
            
            // Format: 001-FERROL-LASEU: Baxi Ferrol 94 - Cadí La Seu 95
            String line = match.getCode() + ": " +
                match.getLocalTeam().getCodeTeam().getName() + " " +
                match.getPointsLocalTeam() + " - " +
                match.getVisitantTeam().getCodeTeam().getName() + " " +
                match.getPointsVisitantTeam();
            
            result += line + "\n";
        }
        
        return result;
    }

    /**
     * showMatches2
     * Shows all matches in a compact table format
     * Alternative format to showMatches() - displays results in a table
     * @return Formatted string with all matches in table format
     */
    public String showMatches2() {
        // Build the header
        String result = String.format("%-20s %-25s %5s   %-25s %5s\n",
                "CODE", "LOCAL TEAM", "PTS", "VISITANT TEAM", "PTS");
        result += "--------------------------------------------------------------------------------------\n";
        
        // Loop through all matches and add each one
        for (int i = 0; i < matches.length; i++) {
            Match match = matches[i];
            
            String line = String.format("%-20s %-25s %5d   %-25s %5d",
                    match.getCode(),
                    match.getLocalTeam().getCodeTeam().getName(),
                    match.getPointsLocalTeam(),
                    match.getVisitantTeam().getCodeTeam().getName(),
                    match.getPointsVisitantTeam());
            
            result += line + "\n";
        }
        
        return result;
    }

    /**
     * showClassification
     * Shows the league classification/standings sorted by points
     * Primary sort: pointsTeam (descending)
     * Secondary sort: pointsFor (descending) - used when teams have same points
     * @return Array of teams sorted by classification
     */
    public Team[] showClassification() {
        // Create a copy of teams array to avoid modifying the original
        Team[] sortedTeams = new Team[teams.length];
        for (int i = 0; i < teams.length; i++) {
            sortedTeams[i] = teams[i];
        }
        
        // Bubble sort 
        // Compare teams and swap if needed
        for (int i = 0; i < sortedTeams.length - 1; i++) {
            for (int j = 0; j < sortedTeams.length - 1 - i; j++) {
                Team current = sortedTeams[j];
                Team next = sortedTeams[j + 1];
                
                // Determine if we need to swap
                boolean shouldSwap = false;
                
                // First criteria: Compare by pointsTeam (higher is better)
                if (current.getPointsTeam() < next.getPointsTeam()) {
                    shouldSwap = true;
                }
                // If same points, compare by pointsFor (higher is better)
                else if (current.getPointsTeam() == next.getPointsTeam()) {
                    if (current.getPointsFor() < next.getPointsFor()) {
                        shouldSwap = true;
                    }
                }
                
                // Swap if needed
                if (shouldSwap) {
                    sortedTeams[j] = next;
                    sortedTeams[j + 1] = current;
                }
            }
        }
        
        return sortedTeams;
    }

} // End of League Class
// END