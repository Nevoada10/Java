package videoGamesUNS;

import java.util.Objects;

/**
 * Platform.java
 * Class representing a gaming platform
 * Contains platform name, developer and release date
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Platform {

//============================
// ATTRIBUTES
//============================

    private String name;
    private String developer;
    private String releaseDate;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a platform with all attributes
     * @param name Platform name
     * @param developer Platform developer
     * @param releaseDate Release date
     */
    public Platform(String name, String developer, String releaseDate) {
        this.name = name;
        this.developer = developer;
        this.releaseDate = releaseDate;
    }

//============================
// METHODS
//============================

    /**
     * Generates hash code based on name (case-insensitive)
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    /**
     * Compares platforms by name (case-insensitive)
     * @param obj Object to compare
     * @return true if platforms are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Platform platform = (Platform) obj;
        return name.equalsIgnoreCase(platform.name);
    }

    /**
     * Returns a string representation of the platform
     * @return Formatted string with platform information
     */
    @Override
    public String toString() {
        return "Platform{" +
               "name='" + name + '\'' +
               ", developer='" + developer + '\'' +
               ", releaseDate='" + releaseDate + '\'' +
               '}';
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the platform name
     * @return Platform name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the platform name
     * @param name Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the developer
     * @return Developer name
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * Sets the developer
     * @param developer Developer to set
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    /**
     * Gets the release date
     * @return Release date
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the release date
     * @param releaseDate Release date to set
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}