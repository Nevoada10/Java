package poker;

/**
 * Hand.java
 * Class representing a poker hand (5 cards)
 * Manages the cards in a player's hand
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Hand {

//============================
// ATTRIBUTES
//============================

    private Card[] cards;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a poker hand with space for 5 cards
     */
    public Hand() {
        this.cards = new Card[5];
    }

//============================
// METHODS
//============================

    /**
     * Adds a card to the hand at specified index
     * @param index Position in the hand (0-4)
     * @param card Card to add
     */
    public void addCard(int index, Card card) {
        if (index >= 0 && index < cards.length) {
            cards[index] = card;
        }
    }

    /**
     * Returns a string representation of the hand
     * Format: [SUIT CODE, SUIT CODE, ...]
     * @return Formatted string with all cards
     */
    @Override
    public String toString() {
        String result = "[";
        
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                result += cards[i].toString();
                
                if (i < cards.length - 1) {
                    result += ", ";
                }
            }
        }
        
        result += "]";
        return result;
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the cards array
     * @return Cards array
     */
    public Card[] getCards() {
        return cards;
    }

    /**
     * Sets the cards array
     * @param cards Cards to set
     */
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
}