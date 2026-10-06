package league;

/**
 * CodeTeam.java
 * Enum class containing basketball team data
 * Stores code, name and city for each team in the league
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public enum CodeTeam {
    FERROL("Baxi Ferrol", "Ferrol"),
    LASEU("Cadí La Seu", "La Seu d'Urgell"),
    ZARAGOZA("Casademont Zaragoza", "Zaragoza"),
    BOSCO("Celta Femxa Zorka", "Pontevedra"),
    ENSINO("Durán Maquinaria Ensino", "Lugo"),
    MURCIA("Hozono Global Jairis", "Murcia"),
    DONOSTIA("IDK Euskotren", "Donostia-San Sebastián"),
    JOVENTUT("Joventut", "Badalona"),
    ARASKI("Kutxabank Araski", "Vitoria-Gasteiz"),
    GERNIKA("Lointek Gernika Bizkaia", "Gernika-Lumo"),
    ESTUDIANTES("Movistar Estudiantes", "Madrid"),
    ARDOI("Osés Construcción Ardoi", "Navarra"),
    SALAMANCA("Perfumerías Avenida", "Salamanca"),
    GIRONA("Spar Girona", "Girona"),
    PALMAS("Spar Gran Canaria", "Palmas de Gran Canaria"),
    VALENCIA("Valencia BC", "Valencia");
    
    // Team attributes
    private final String name;
    private final String city;


//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor for CodeTeam enum
     * @param name Team name
     * @param city Team city
     */
    private CodeTeam(String name, String city) {
        this.name = name;
        this.city = city;
        }

        
//============================
// GETTERS
//============================

    /**
     * Gets the team name
     * @return Team name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the team city
     * @return Team city
     */
    public String getCity() {
        return city;
    }

}// End of CodeTeam Enum Class
// END