package videoGamesUNS;

import java.util.Objects;

/**
 * Creator.java
 * Class representing a video game creator
 * Contains creator name, birth date and country
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Creator {

//============================
// ATTRIBUTES
//============================

    private String name;
    private String birthDate;
    private String country;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a creator with all attributes
     * @param name Creator name
     * @param birthDate Birth date
     * @param country Country
     */
    public Creator(String name, String birthDate, String country) {
        this.name = name;
        this.birthDate = birthDate;
        this.country = country;
    }

//============================
// METHODS
//============================

    /**
     * Generates hash code based on name and birthDate (case-insensitive)
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), birthDate.toLowerCase());
    }

    /**
     * Compares creators by name and birthDate (case-insensitive)
     * @param obj Object to compare
     * @return true if creators are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Creator creator = (Creator) obj;
        return name.equalsIgnoreCase(creator.name) && 
               birthDate.equalsIgnoreCase(creator.birthDate);
    }

    /**
     * Returns a string representation of the creator
     * @return Formatted string with creator information
     */
    @Override
    public String toString() {
        return "Creator{" +
               "name='" + name + '\'' +
               ", birthDate='" + birthDate + '\'' +
               ", country='" + country + '\'' +
               '}';
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the creator name
     * @return Creator name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the creator name
     * @param name Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the birth date
     * @return Birth date
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date
     * @param birthDate Birth date to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets the country
     * @return Country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country
     * @param country Country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
}