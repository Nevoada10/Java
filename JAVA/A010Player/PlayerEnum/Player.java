package A010Player.PlayerEnum;

/**
 * Player.java
 * Class representing a football player
 * Contains personal information and game statistics
 * @Author Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Player {

// ================================== 
// VARIABLES AND CONSTANTS
// ==================================

    // Initial variables (instance attributes)
    private String name;
    private String surname;
    private float height;
    private float weight;
    private byte age = 18; // Default value
    private Sex sex = Sex.NOTDEFINED; // This uses the Sex enum to set the default value to NOTDEFINED
    private int points = 100; // Default value
    private Team team;
    private Position position; // This is a variable named "position" of the position enum type. TYPE -> Variable name
    private boolean active = true; // Default value
    private byte cards = 0;
    
    // Constants
    private static final int INITIAL_POINTS = 100;
    private static final byte INITIAL_AGE = 18;

// ================================== 
// CONSTRUCTORS
// ==================================
    
    /**
     * Constructor with all attributes except cards, CONSTRUCTOR 1
     * @param name Player's name
     * @param surname Player's surname
     * @param height Player's height
     * @param weight Player's weight
     * @param age Player's age
     * @param sex Player's sex
     * @param points Player's points
     * @param team Player's team
     * @param position Player's position
     * @param active Player's active status
     */
    public Player(String name, String surname, float height, float weight, byte age, Sex sex, int points, Team team, Position position, boolean active) {
        this.name = name;
        this.surname = surname;
        this.height = height;
        this.weight = weight;
        this.age = (age < 18) ? (byte) 18 : age; // If age is less than 18, set it to 18
        this.sex = sex;
        this.points = (points < 100) ? 100 : points; // If points are less than 100, set them to 100
        this.team = team;
        this.position = position;
        this.active = active;
        this.cards = 0; // Default value
    }
    
    /**
     * CONSTRUCTOR 2
     * @param name Player's name
     * @param surname Player's surname
     * @param height Player's height
     * @param weight Player's weight
     * @param team Player's team
     * @param position Player's position
     */
    public Player(String name, String surname, float height, float weight, Team team, Position position) {
        this.name = name;
        this.surname = surname;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.position = position;
        this.age = INITIAL_AGE;
        this.sex = Sex.NOTDEFINED;
        this.points = INITIAL_POINTS;
        this.active = true;
        this.cards = 0;
    }
    
    /**
     * CONSTRUCTOR 3
     * @param name Player's name
     * @param surname Player's surname
     * @param sex Player's sex
     * @param team Player's team
     * @param position Player's position
     */
    public Player(String name, String surname, Sex sex, Team team, Position position) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.team = team;
        this.position = position;
        this.age = INITIAL_AGE;
        this.points = INITIAL_POINTS;
        this.active = true;
        this.cards = 0;
        this.height = 0.0f;
        this.weight = 0.0f;
    }
    
    /**
     * CONSTRUCTOR 4
     * @param name Player's name
     * @param surname Player's surname
     * @param age Player's age
     * @param sex Player's sex
     * @param team Player's team
     * @param position Player's position
     */
    public Player(String name, String surname, byte age, Sex sex, Team team, Position position) {
        this.name = name;
        this.surname = surname;
        this.age = (age < 18) ? (byte) 18 : age;
        this.sex = sex;
        this.team = team;
        this.position = position;
        this.points = INITIAL_POINTS;
        this.active = true;
        this.cards = 0;
        this.height = 0.0f;
        this.weight = 0.0f;
    }

// ===========================================================================
// PLAYER METHODS
// ===========================================================================

    /**
     * Converts player information to a String
     * @return String representation of the player
     */
    public String toString() {
        return "Player: " + name + " " + surname + 
               " | Sex: " + sex +
               " | Team: " + (team != null ? team.getName() : "N/A") +
               " | Position: " + position +
               " | Points: " + points +
               " | Age: " + age +
               " | Active: " + active;
    }
    
    /**
     * Increases player points, only if the player is active and cards < 2
     * @param points Points to add
     * @return true if points were increased successfully
     */
    public boolean increasePoints(int points) {
        if ( this.active && this.cards < 2) {
            this.points += points;
            return true;
        }
        return false;
    }
    
    /**
     * Decreases player points,only if the player is active, cards < 2 and points are > than 0
     * @param points Points to subtract
     * @return true if points were decreased successfully
     */
    public boolean decreasePoints(int points) {
        if (this.active && this.cards < 2 && points > 0) {
            this.points -= points;
            return true;
        }
        return false;
    }
    
    /**
     * Add 1 to number of cards if player is active and cards < 2
     * @return true if card was given successfully
     */
    public boolean giveCard() {
       if (this.active && this.cards < 2) {
           this.cards+=1;
           return true;
       }
       return false;
    }
    

// =====================
// GETTER METHODS
// =====================
    
    /**
     * Gets the player's name
     * @return Player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the player's surname
     * @return Player's surname
     */
    public String getSurname() {
        return surname;
    }
    
    /**
     * Gets the player's height
     * @return Player's height
     */
    public float getHeight() {
        return height;
    }
    
    /**
     * Gets the player's weight
     * @return Player's weight
     */
    public float getWeight() {
        return weight;
    }
    
    /**
     * Gets the player's age
     * @return Player's age
     */
    public byte getAge() {
        return age;
    }
    
    /**
     * Gets the player's sex
     * @return Player's sex
     */
    public Sex getSex() {
        return sex;
    }
    
    /**
     * Gets the player's points
     * @return Player's points
     */
    public int getPoints() {
        return points;
    }
    
    /**
     * Gets the player's team
     * @return Player's team
     */
    public Team getTeam() {
        return team;
    }
    
    /**
     * Gets the player's position
     * @return Player's position
     */
    public Position getPosition() {
        return position;
    }
    
    /**
     * Checks if the player is active
     * @return true if player is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     * Gets the number of cards the player has
     * @return Number of cards
     */
    public byte getCards() {
        return cards;
    }

// =====================
// SETTER METHODS
// =====================
    
    /**
     * Sets the player's name
     * @param name New name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the player's surname
     * @param surname New surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     * Sets the player's height
     * @param height New height
     */
    public void setHeight(float height) {
        this.height = height;
    }
    
    /**
     * Sets the player's weight
     * @param weight New weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }
    
    /**
     * Sets the player's age
     * @param age New age
     */
    public void setAge(byte age) {
        this.age = age;
    }
    
    /**
     * Sets the player's sex
     * @param sex New sex
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }
    
    /**
     * Sets the player's points
     * @param points New points value
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    /**
     * Sets the player's team
     * @param team New team
     */
    public void setTeam(Team team) {
        this.team = team;
    }
    
    /**
     * Sets the player's position
     * @param position New position
     */
    public void setPosition(Position position) {
        this.position = position;
    }
    
    /**
     * Sets the player's active status
     * @param active New active status
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}