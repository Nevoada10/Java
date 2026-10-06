package videoGamesUNS;

/**
 * Rating.java
 * Enum class containing PEGI ratings
 * Stores name and minimum age for each rating
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public enum Rating {
    P3("PEGI-3", 3),
    P7("PEGI-7", 7),
    P12("PEGI-12", 12),
    P16("PEGI-16", 16),
    P18("PEGI-18", 18);

    // Rating attributes
    private final String name;
    private final int age;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor for Rating enum
     * @param name Rating name
     * @param age Minimum age
     */
    private Rating(String name, int age) {
        this.name = name;
        this.age = age;
    }

//============================
// GETTERS
//============================

    /**
     * Gets the rating name
     * @return Rating name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the minimum age
     * @return Minimum age
     */
    public int getAge() {
        return age;
    }

//============================
// METHODS
//============================

    /**
     * Returns a string representation of the rating
     * @return Rating name
     */
    @Override
    public String toString() {
        return name;
    }
}