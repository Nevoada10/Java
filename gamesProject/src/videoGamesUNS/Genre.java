package videoGamesUNS;

/**
 * Genre.java
 * Enum class containing video game genres
 * Stores name and description for each genre
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public enum Genre {
    ACTION("Action", "Games focused on hand-eye coordination and reaction time."),
    ADVENTURE("Adventure", "Games centered on exploration, narrative, and puzzle-solving."),
    RPG("Role-playing Game", "Games where players assume character roles in a fictional world."),
    PLATFORMS("Platforms", "Games involving jumping between platforms and overcoming obstacles."),
    SPORTS("Sports", "Games that simulate real sports."),
    SHOOTER("Shooter", "Games centered on using weapons to eliminate enemies."),
    SURVIVAL("Survival", "Games focused on character survival in a hostile environment."),
    MOBA("Multiplayer Online Battle Arena", "Games focused on multiplayer.");

    // Genre attributes
    private final String name;
    private final String description;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor for Genre enum
     * @param name Genre name
     * @param description Genre description
     */
    private Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

//============================
// GETTERS
//============================

    /**
     * Gets the genre name
     * @return Genre name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the genre description
     * @return Genre description
     */
    public String getDescription() {
        return description;
    }

//============================
// METHODS
//============================

    /**
     * Returns a string representation of the genre
     * @return Genre name
     */
    @Override
    public String toString() {
        return name;
    }
}