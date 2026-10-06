package poker;

/**
 * Value.java
 * Enum class containing poker card values
 * Stores number and code for each card value
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public enum Value {
    ACE(14, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K");

    // Value attributes
    private final int number;
    private final String code;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor for Value enum
     * @param number Numeric value of the card
     * @param code String code of the card
     */
    private Value(int number, String code) {
        this.number = number;
        this.code = code;
    }

//============================
// GETTERS
//============================

    /**
     * Gets the numeric value
     * @return Numeric value
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the card code
     * @return Card code
     */
    public String getCode() {
        return code;
    }

//============================
// METHODS
//============================

    /**
     * Returns a string representation of the value
     * @return Card code
     */
    @Override
    public String toString() {
        return code;
    }
}