package poker;

/**
 * Card.java
 * Class representing a poker card
 * Contains suit and value
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Card {

//============================
// ATTRIBUTES
//============================

    private Suit suit;
    private Value value;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a card with specified suit and value
     * @param suit Card suit
     * @param value Card value
     */
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

//============================
// METHODS
//============================

    /**
     * Returns a string representation of the card
     * Format: SUIT CODE (e.g., HEARTS A)
     * @return Formatted string with card information
     */
    @Override
    public String toString() {
        return suit + " " + value.getCode();
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the card suit
     * @return Card suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Sets the card suit
     * @param suit Suit to set
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * Gets the card value
     * @return Card value
     */
    public Value getValue() {
        return value;
    }

    /**
     * Sets the card value
     * @param value Value to set
     */
    public void setValue(Value value) {
        this.value = value;
    }
}