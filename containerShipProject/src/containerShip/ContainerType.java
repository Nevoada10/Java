package containerShip;

/**
 * ContainerType.java
 * Enum class containing container types
 * Stores description for each container type
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public enum ContainerType {
    STANDARD("Standard Type"),
    FREEZE("Refrigerated"),
    TANK("Liquid Tank"),
    OPEN_TOP("No ceiling");

    // Container type attribute
    private final String description;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor for ContainerType enum
     * @param description Container type description
     */
    private ContainerType(String description) {
        this.description = description;
    }

//============================
// GETTERS
//============================

    /**
     * Gets the container type description
     * @return Container type description
     */
    public String getDescription() {
        return description;
    }

//============================
// METHODS
//============================

    /**
     * Returns a string representation of the container type
     * @return String with format "TYPE: description"
     */
    @Override
    public String toString() {
        return this.name() + ": " + description;
    }
}