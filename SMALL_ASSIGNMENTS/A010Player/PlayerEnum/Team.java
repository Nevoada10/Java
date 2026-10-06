package A010Player.PlayerEnum;

/**
 * Team.java
 * Enum representing football teams
 * Each team has a name and a country
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * 
 * An enum is a custom data type (not a String) that defines a fixed and limited
 * set of allowed values. It works like a dropdown menu in code: you can only
 * choose one of the predefined options, which prevents invalid data, typos,
 * and logical errors. Enums make code safer, clearer, and easier to maintain
 * by ensuring that variables can never hold unexpected values.
 */
public enum Team {
    FCB("Futbol Club Barcelona", "Spain"),
    RM("Real Madrid", "Spain"),
    SFC("Sevilla FC", "Spain"),
    PSG("Paris Saint Germain", "France"),
    BNF("Benfica", "Portugal"),
    JUV("Juventus", "Italy");
    
    final private String teamName;
    final private String teamCountry;
    
    /**
     * Constructor for the Team enum
     * @param teamName The full name of the team
     * @param teamCountry The country where the team is from
     */
    private Team(String teamName, String teamCountry) {
        this.teamName = teamName;
        this.teamCountry = teamCountry;
    }

    
    /**
     * Gets the name of the team
     * @return The team name
     */
    public String getName() {
        return teamName;
    }
    
    /**
     * Gets the country of the team
     * @return The country where the team is from
     */
    public String getCountry() {
        return teamCountry;
    }
}