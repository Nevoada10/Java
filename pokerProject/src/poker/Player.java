package poker;

/**
 * Player.java
 * Class representing a poker player
 * Contains player information and their hand
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Player {

//============================
// ATTRIBUTES
//============================

    // Static attribute for ID generation
    private static int nextPlayerNumber = 1;

    // Player attributes
    private String code;
    private String firstName;
    private String lastName;
    private int points;
    private Hand hand;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a player with first and last name
     * Generates unique code and initializes hand
     * @param firstName Player's first name
     * @param lastName Player's last name
     */
    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = generateCode();
        this.hand = new Hand();
        this.points = 0;
    }

//============================
// METHODS
//============================

    /**
     * Generates unique player code
     * Format: XXX-AB (e.g., 001-PB)
     * XXX: 3-digit number with leading zeros
     * A: First letter of first name
     * B: First letter of last name
     * @return Generated player code
     */
    private String generateCode() {
        String number = String.format("%03d", nextPlayerNumber);
        nextPlayerNumber++;
        
        char firstInitial = firstName.charAt(0);
        char lastInitial = lastName.charAt(0);
        
        return number + "-" + firstInitial + lastInitial;
    }

    /**
     * Shows the player's cards
     * @return String with player's hand
     */
    public String showCards() {
        return hand.toString();
    }

    /**
     * Returns a string representation of the player
     * Format: CODE POINTS (e.g., 001-PB 42)
     * @return Formatted string with player code and points
     */
    @Override
    public String toString() {
        return code + " " + points;
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the player code
     * @return Player code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the player code
     * @param code Code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the first name
     * @return First name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name
     * @param firstName First name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name
     * @return Last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name
     * @param lastName Last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the player points
     * @return Player points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the player points
     * @param points Points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the player's hand
     * @return Player's hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Sets the player's hand
     * @param hand Hand to set
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     * Resets the player number counter to 1
     * Useful for testing purposes
     */
    public static void resetPlayerCounter() {
        nextPlayerNumber = 1;
    }
}