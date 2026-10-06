package A011TeamMatch.Match;

/**
 * Match.java
 * Class Match - Represents a soccer match between two teams
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Match {
    

// =========================================================================
//  Match.java VARIABLES AND CONSTANTS 
// =========================================================================

    // Track the next available ID
    private static int nextId = 1; // Initialize to 1, global.
    
    // Attributes 
    private Team localTeam;
    private Team visitantTeam;
    private int goalsLocalTeam;
    private int goalsVisitantTeam;
    private final int id; // ID of the match
    
// =========================================================================
// Match.java CONSTRUCTOR 
// =========================================================================

    /**
     * Constructor that assigns the id (sequential) to the Match object
     * @param localTeam The local/home team
     * @param visitantTeam The visiting/away team
     */
    public Match(Team localTeam, Team visitantTeam) {
        this.localTeam = localTeam;
        this.visitantTeam = visitantTeam;
        this.goalsLocalTeam = 0;  // Initialize goals to 0
        this.goalsVisitantTeam = 0;  // Initialize goals to 0
        this.id = nextId;  // Assign current nextId
        nextId+=1;  // Increment for the next Match object
    }
    
// =========================================================================
// Match.java METHODS 
// =========================================================================
    
    /**
     * Returns the next free ID that will be assigned
     * This method is static as it depends on the class, not the object
     * @return The next available ID
     */
    public static int showId() {
        return nextId;
    }
    
    /**
     * Plays the match by randomly assigning 0-4 goals to each team
     * Stores the values in goalsLocalTeam and goalsVisitantTeam
     */
    public void playMatch() {
        this.goalsLocalTeam = (int) (Math.random() * 5);  // Random number between 0 and 4
        this.goalsVisitantTeam = (int) (Math.random() * 5);  // Random number between 0 and 4
    }
    
    /**
     * Ends the match and assigns points to the teams
     * Winner gets 3 points, in case of draw both teams get 1 point
     * @return String indicating the winner or if it's a draw
     */
    public String endMatch() {
        if (goalsLocalTeam > goalsVisitantTeam) { // If local team wins
            // Local team wins
            localTeam.incrementPoints(3);
            return "The winner is: " + localTeam.getNameTeam();
        } else if (goalsVisitantTeam > goalsLocalTeam) { // If visitant team wins
            // Visitant team wins
            visitantTeam.incrementPoints(3);
            return "The winner is: " + visitantTeam.getNameTeam();
        } else { // If it's a draw
            localTeam.incrementPoints(1);
            visitantTeam.incrementPoints(1);
            return "It is a draw.";
        }
    }
    
    /** 
     * Returns a string representation of the Match object
     * @return String with format: Match [localTeam=..., visitantTeam=..., goalsLocalTeam=..., goalsVisitantTeam=..., id=...]
     */
    @Override
    public String toString() {
        return "Match [localTeam=" + localTeam.toString() + 
               ", visitantTeam=" + visitantTeam.toString() + 
               ", goalsLocalTeam=" + goalsLocalTeam + 
               ", goalsVisitantTeam=" + goalsVisitantTeam + 
               ", id=" + id + "]";
    }
    
// =========================================================================
// Match.java GETTERS AND SETTERS 
// =========================================================================
    
    /**
     * Gets the local team
     * @return The local team object
     */
    public Team getLocalTeam() {
        return localTeam;
    }
    
    /**
     * Sets the local team
     * @param localTeam The new local team
     */
    public void setLocalTeam(Team localTeam) {
        this.localTeam = localTeam;
    }
    
    /**
     * Gets the visitant team
     * @return The visitant team object
     */
    public Team getVisitantTeam() {
        return visitantTeam;
    }
    
    /**
     * Sets the visitant team
     * @param visitantTeam The new visitant team
     */
    public void setVisitantTeam(Team visitantTeam) {
        this.visitantTeam = visitantTeam;
    }
    
    /**
     * Gets the goals scored by the local team
     * @return Number of goals by local team
     */
    public int getGoalsLocalTeam() {
        return goalsLocalTeam;
    }
    
    /**
     * Sets the goals scored by the local team
     * @param goalsLocalTeam Number of goals by local team
     */
    public void setGoalsLocalTeam(int goalsLocalTeam) {
        this.goalsLocalTeam = goalsLocalTeam;
    }
    
    /**
     * Gets the goals scored by the visitant team
     * @return Number of goals by visitant team
     */
    public int getGoalsVisitantTeam() {
        return goalsVisitantTeam;
    }
    
    /**
     * Sets the goals scored by the visitant team
     * @param goalsVisitantTeam Number of goals by visitant team
     */
    public void setGoalsVisitantTeam(int goalsVisitantTeam) {
        this.goalsVisitantTeam = goalsVisitantTeam;
    }
    
    /**
     * Gets the match ID
     * @return The match ID
     */
    public int getId() {
        return id;
    }
}