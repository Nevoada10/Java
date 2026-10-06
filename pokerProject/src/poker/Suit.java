package poker;

/**
 * Suit.java
 * Enum class containing poker card suits
 * Stores the four suits: Hearts, Diamonds, Clubs, Spades
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public enum Suit {
    HEARTS,
    DIAMONDS,
    CLUBS,
    SPADES;

//============================
// METHODS
//============================

    /**
     * Returns a string representation of the suit
     * @return Suit name
     */
    @Override
    public String toString() {
        return this.name();
    }
}