package league;

/**
 * Team.java
 * Class containing basketball team data
 * Stores code, name and city for each team in the league
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Team {
    
    // Track the next available ID
    private static int nextId = 1;

    // Team attributes
    private CodeTeam codeTeam; // Team code from CodeTeam enum
    private int pointsTeam; // Points of the team in the league
    private int pointsFor; // Number of points scored by the team
    private int pointsAgainst; // Number of points conceded by the team
    private int gamesPlayed; // Number of games played 
    private int gamesWon; 
    private int gamesLost; 
    private int gamesTied;
    private final int id; 


//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * @param codeTeam Team code from CodeTeam enum
     */
    public Team(CodeTeam codeTeam) {
        this.codeTeam = codeTeam;
        this.pointsTeam = 0;
        this.pointsFor = 0;
        this.pointsAgainst = 0;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.gamesTied = 0;
        this.id = nextId;
        nextId += 1;
    }


//============================
// METHODS
//============================

    /**
     * Adds points to the team (only 1 or 3 points allowed)
     * @param points Points to add (1 or 3)
     */
    public void addPointsTeam(int points) {
        if (points == 1 || points == 3) {
            this.pointsTeam += points;
        }
    }

    /**
     * Adds points scored by the team
     * @param points Points to add (must be positive)
     */
    public void addPointsFor(int points) {
        if (points > 0) {
            this.pointsFor += points;
        }
    }

    /**
     * Adds points conceded by the team
     * @param points Points to add (must be positive)
     */
    public void addPointsAgainst(int points) {
        if (points > 0) {
            this.pointsAgainst += points;
        }
    }

    /**
     * Increments games played by 1
     * @param points Must be positive to increment
     */
    public void increaseGamesPlayed(int points) {
        if (points > 0) {
            this.gamesPlayed += 1;
        }
    }

    /**
     * Increments games won by 1
     * @param points Must be positive to increment
     */
    public void increaseGamesWon(int points){
        if (points > 0) {
            this.gamesWon += 1;
        }
    }

    /**
     * Increments games lost by 1
     * @param points Must be positive to increment
     */
    public void increaseGamesLost(int points){
        if (points > 0) {
            this.gamesLost += 1;
        }
    }   

    /**
     * Increments games tied by 1
     * @param points Must be positive to increment
     */
    public void increaseGamesTied(int points){
        if (points > 0) {
            this.gamesTied += 1;
        }
    }

    /**
     * Returns a string representation of the team
     * Shows all team data with proper spacing
     * @return Formatted string with team information
     */
    @Override 
    public String toString() {
        String header = String.format("%-5s %-15s %5s %5s %5s %5s %5s %5s %5s",
        id,
        codeTeam,
        pointsTeam,
        pointsFor,
        pointsAgainst, 
        gamesPlayed,
        gamesWon,
        gamesLost,
        gamesTied);
        return header;
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the team code
     * @return Team code from CodeTeam enum
     */
    public CodeTeam getCodeTeam() {
        return codeTeam;
    }

    /**
     * Sets the team code
     * @param codeTeam Team code from CodeTeam enum
     */
    public void setCodeTeam(CodeTeam codeTeam) {
        this.codeTeam = codeTeam;
    }

    /**
     * Gets the team points in the league
     * @return Points of the team
     */
    public int getPointsTeam() {
        return pointsTeam;
    }

    /**
     * Sets the team points in the league
     * @param pointsTeam Points to set
     */
    public void setPointsTeam(int pointsTeam) {
        this.pointsTeam = pointsTeam;
    }

    /**
     * Returns the next available ID (static)
     * @return Next ID to be assigned
     */
    public static int showId() {
        return nextId;
    }

    /**
     * Gets the team ID
     * @return Team ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the points scored by the team
     * @return Points scored
     */
    public int getPointsFor() {
        return pointsFor;
    }

    /**
     * Sets the points scored by the team
     * @param pointsFor Points to set
     */
    public void setPointsFor(int pointsFor) {
        this.pointsFor = pointsFor;
    }

    /**
     * Gets the points conceded by the team
     * @return Points conceded
     */
    public int getPointsAgainst() {
        return pointsAgainst;
    }

    /**
     * Sets the points conceded by the team
     * @param pointsAgainst Points to set
     */
    public void setPointsAgainst(int pointsAgainst) {
        this.pointsAgainst = pointsAgainst;
    }

    /**
     * Gets the number of games played
     * @return Games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Sets the number of games played
     * @param gamesPlayed Games to set
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * Gets the number of games lost
     * @return Games lost
     */
    public int getGamesLost() {
        return gamesLost;
    }

    /**
     * Sets the number of games lost
     * @param gamesLost Games to set
     */
    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    /**
     * Gets the number of games tied
     * @return Games tied
     */
    public int getGamesTied() {
        return gamesTied;
    }
    
    /**
     * Sets the number of games tied
     * @param gamesTied Games to set
     */
    public void setGamesTied(int gamesTied) {
        this.gamesTied = gamesTied;
    }

    /**
     * Gets the number of games won
     * @return Games won
     */
    public int getGamesWon() {
        return gamesWon;
    }
    
    /**
     * Sets the number of games won
     * @param gamesWon Games to set
     */
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

}// End of Team Class
// END