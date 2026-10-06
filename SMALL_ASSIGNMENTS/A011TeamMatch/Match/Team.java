package A011TeamMatch.Match;

/** 
 * Team.java
 * Class Team - Represents a soccer team in a match
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Team {
    
// ================================================
// Team.Java VARIABLES AND CONSTANTS
// ================================================

    // Variable to track the next available ID
    private static int nextId = 1;
    
    // Attributes for the object Team
    private String nameTeam;
    private int pointsTeam;
    private int id;
    

// ================================================
// Team.Java CONSTRUCTOR
// ================================================

    /**
     * Constructor that assigns the id (sequential) to the Team object
     * @param nameTeam The name of the team
     */
    public Team(String nameTeam) {
        this.nameTeam = nameTeam;
        this.pointsTeam = 0;  // Initialize points to 0
        this.id = nextId;  // Assign current nextId
        nextId++;  // Increment for the next Team object
    }
    
// ================================================
// Team.Java METHODS
// ================================================
    
    /**
     * Increments the team's points by n
     * @param n Number of points to add
     */
    public void incrementPoints(int n) {
        this.pointsTeam += n;
    }
    
    /**
     * Returns the next free ID that will be assigned
     * This method is static as it depends on the class, not the object
     * @return The next available ID
     */
    public static int showId() {
        return nextId;
    }
    
    /**
     * Returns a string representation of the Team object
     * @return String with format: Team [nameTeam=..., pointsTeam=..., id=...]
     */
    @Override
    public String toString() {
        return "Team{name='" + nameTeam + "', points=" + pointsTeam + ", id=" + id + "}";
    }
    
    
// ================================================
// Team.Java GETTERS AND SETTERS
// ================================================
    
    /**
     * Gets the team name
     * @return The name of the team
     */
    public String getNameTeam() {
        return nameTeam;
    }
    
    /**
     * Sets the team name
     * @param nameTeam The new name for the team
     */
    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }
    
    /**
     * Gets the team points
     * @return The points of the team
     */
    public int getPointsTeam() {
        return pointsTeam;
    }
    
    /**
     * Sets the team points
     * @param pointsTeam The new points for the team
     */
    public void setPointsTeam(int pointsTeam) {
        this.pointsTeam = pointsTeam;
    }
}