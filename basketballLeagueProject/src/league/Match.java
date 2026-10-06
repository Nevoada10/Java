package league;

/**
 * Match.java
 * Class containing basketball match data
 * Stores local team, visitant team, points of each team and match code
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Match {


//============================
// ATTRIBUTES
//============================

    // Next match number tracker (static)
    private static int nextMatchNumber = 1;

    // Match attributes
    private Team localTeam;
    private Team visitantTeam;
    private byte pointsLocalTeam;
    private byte pointsVisitantTeam;
    private String code;


//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a match between two teams and generates unique code
     * @param localTeam Local team
     * @param visitantTeam Visitant team
     */
    public Match(Team localTeam, Team visitantTeam) {
        this.localTeam = localTeam;
        this.visitantTeam = visitantTeam;
        this.pointsLocalTeam = 0;
        this.pointsVisitantTeam = 0;
        this.code = generateCode();
    }


//============================
// METHODS
//============================

    /**
     * Generates the match code with format xxx-A-B
     * xxx: auto-incremental number with 3 digits and leading zeros
     * A: code of local team
     * B: code of visitant team
     * @return Generated match code
     */
    private String generateCode() {
        String number = String.format("%03d", nextMatchNumber);
        nextMatchNumber++;
        
        String localCode = localTeam.getCodeTeam().name();
        String visitantCode = visitantTeam.getCodeTeam().name();
        
        return number + "-" + localCode + "-" + visitantCode;
    }

    /**
     * Simulates match play
     * Randomly assigns between 50 and 100 points (inclusive) to each team
     */
    public void playMatch() { 
        this.pointsLocalTeam = (byte) (Math.random() * 51 + 50);
        this.pointsVisitantTeam = (byte) (Math.random() * 51 + 50);
    }

    /**
     * Processes match results and updates both teams' statistics
     * - Increments games played for both teams
     * - Awards points: 3 for win, 1 for tie, 0 for loss
     * - Updates points for/against for both teams
     * - Updates games won/lost/tied counters
     * @return Formatted string with both teams' statistics
     */
    public String endMatch() {
        // Both teams increment games played
        localTeam.increaseGamesPlayed(pointsLocalTeam);
        visitantTeam.increaseGamesPlayed(pointsVisitantTeam);
        
        // Add points scored to pointsFor
        localTeam.addPointsFor(pointsLocalTeam);
        visitantTeam.addPointsFor(pointsVisitantTeam);
        
        // Add points conceded to pointsAgainst
        localTeam.addPointsAgainst(pointsVisitantTeam);
        visitantTeam.addPointsAgainst(pointsLocalTeam);
        
        // Determine winner and update statistics
        if (pointsLocalTeam > pointsVisitantTeam) {
            // Local team wins
            localTeam.addPointsTeam(3);
            localTeam.increaseGamesWon(pointsLocalTeam);
            visitantTeam.increaseGamesLost(pointsVisitantTeam);
            
        } else if (pointsVisitantTeam > pointsLocalTeam) {
            // Visitant team wins
            visitantTeam.addPointsTeam(3);
            visitantTeam.increaseGamesWon(pointsVisitantTeam);
            localTeam.increaseGamesLost(pointsLocalTeam);
            
        } else {
            // Tie
            localTeam.addPointsTeam(1);
            visitantTeam.addPointsTeam(1);
            localTeam.increaseGamesTied(pointsLocalTeam);
            visitantTeam.increaseGamesTied(pointsVisitantTeam);
        }
        
        // Build and return formatted result
        String header = String.format("%-5s %-15s %5s %5s %5s %5s %5s %5s %5s",
                "ID", "CODE", "P", "PF", "PA", "GP", "GW", "GL", "GT");
        String separator = "---------------------------------------------------------------";
        
        return header + "\n" + separator + "\n" + 
            localTeam.toString() + "\n" + 
            visitantTeam.toString();
    }

    /**
     * Returns the next match number (static)
     * @return Next match number to be assigned
     */
    public static int showId() {
        return nextMatchNumber;
    }

    /**
     * Resets the match number counter to 1
     * Useful for testing purposes 
     * Custom method
     */
    public static void resetMatchCounter() {
        nextMatchNumber = 1;
    }

    /**
     * Returns a string representation of the match
     * Format: code localTeamName localPoints visitantTeamName visitantPoints
     * @return Formatted string with match information
     */
    @Override
    public String toString() {
        return code + " " + 
            localTeam.getCodeTeam().getName() + " " + 
            pointsLocalTeam + " " + 
            visitantTeam.getCodeTeam().getName() + " " + 
            pointsVisitantTeam;
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the match code
     * @return Match code (format: xxx-A-B)
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the match code
     * @param code Match code to set (format: xxx-A-B)
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the local team points
     * @return Local team points
     */
    public byte getPointsLocalTeam() {
        return pointsLocalTeam;
    }

    /**
     * Sets the local team points
     * @param pointsLocalTeam Points to set
     */
    public void setPointsLocalTeam(byte pointsLocalTeam) {
        this.pointsLocalTeam = pointsLocalTeam;
    }

    /**
     * Gets the visitant team points
     * @return Visitant team points
     */
    public byte getPointsVisitantTeam() {
        return pointsVisitantTeam;
    }

    /**
     * Sets the visitant team points
     * @param pointsVisitantTeam Points to set
     */
    public void setPointsVisitantTeam(byte pointsVisitantTeam) {
        this.pointsVisitantTeam = pointsVisitantTeam;
    }

    /**
     * Gets the local team
     * @return Local team
     */
    public Team getLocalTeam() {
        return localTeam;
    }

    /**
     * Sets the local team
     * @param localTeam Team to set as local
     */
    public void setLocalTeam(Team localTeam) {
        this.localTeam = localTeam;
    }

    /**
     * Gets the visitant team
     * @return Visitant team
     */
    public Team getVisitantTeam() {
        return visitantTeam;
    }

    /**
     * Sets the visitant team
     * @param visitantTeam Team to set as visitant
     */
    public void setVisitantTeam(Team visitantTeam) {
        this.visitantTeam = visitantTeam;
    }
} // End of Match Class
// END