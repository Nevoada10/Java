package poker;

/**
 * Poker.java
 * Class representing a poker game
 * Manages players, deals cards, and calculates rankings
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Poker {

//============================
// ATTRIBUTES
//============================

    private Player[] players;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a poker game with given players
     * @param players Array of players
     */
    public Poker(Player[] players) {
        this.players = players;
    }

//============================
// METHODS
//============================

    /**
     * Initializes the poker game
     * Sets all players' points to 0
     */
    public void initialize() {
        for (int i = 0; i < players.length; i++) {
            players[i].setPoints(0);
        }
    }

    /**
     * Generates and returns a random card
     * @return Random card
     */
    public Card giveCard() {
        // Get random suit
        Suit[] suits = Suit.values();
        int randomSuitIndex = (int) (Math.random() * suits.length);
        Suit randomSuit = suits[randomSuitIndex];
        
        // Get random value
        Value[] values = Value.values();
        int randomValueIndex = (int) (Math.random() * values.length);
        Value randomValue = values[randomValueIndex];
        
        return new Card(randomSuit, randomValue);
    }

    /**
     * Gives a hand of 5 random cards to a player
     * @param player Player to receive the hand
     * @return The created hand
     */
    public Hand giveHand(Player player) {
        Hand hand = new Hand();
        
        // Add 5 random cards to the hand
        for (int i = 0; i < 5; i++) {
            Card card = giveCard();
            hand.addCard(i, card);
        }
        
        // Assign hand to player
        player.setHand(hand);
        
        return hand;
    }

    /**
     * Calculates the rank (sum of card values) for a player
     * Assigns the rank to player's points
     * @param player Player to calculate rank for
     * @return Player's rank
     */
    public int getRank(Player player) {
        int rank = 0;
        Card[] cards = player.getHand().getCards();
        
        // Sum all card values
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                rank += cards[i].getValue().getNumber();
            }
        }
        
        // Assign rank to player's points
        player.setPoints(rank);
        
        return rank;
    }

    /**
     * Returns players ordered by points (descending)
     * @return Array of players sorted by points
     */
    public Player[] orderClassification() {
        // Create a copy to avoid modifying original
        Player[] sortedPlayers = new Player[players.length];
        for (int i = 0; i < players.length; i++) {
            sortedPlayers[i] = players[i];
        }
        
        // Bubble sort by points (descending)
        for (int i = 0; i < sortedPlayers.length - 1; i++) {
            for (int j = 0; j < sortedPlayers.length - 1 - i; j++) {
                if (sortedPlayers[j].getPoints() < sortedPlayers[j + 1].getPoints()) {
                    // Swap
                    Player temp = sortedPlayers[j];
                    sortedPlayers[j] = sortedPlayers[j + 1];
                    sortedPlayers[j + 1] = temp;
                }
            }
        }
        
        return sortedPlayers;
    }

    /**
     * Returns a string representation of the poker game
     * Shows all players with their codes and points
     * @return Formatted string with all players
     */
    @Override
    public String toString() {
        String result = "";
        
        for (int i = 0; i < players.length; i++) {
            result += players[i].toString();
            
            if (i < players.length - 1) {
                result += "\n";
            }
        }
        
        return result;
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the players array
     * @return Players array
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Sets the players array
     * @param players Players to set
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }
}