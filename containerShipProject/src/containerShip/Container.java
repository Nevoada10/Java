package containerShip;

/**
 * Container.java
 * Class representing a shipping container
 * Stores container data including id, location, weight, type and transport cost
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Container {

//============================
// ATTRIBUTES
//============================

    // Static attribute for ID generation
    private static int nextIdNumber = 1;

    // Container attributes
    private String id;
    private int location;
    private int weight;
    private ContainerType type;
    private double transportCost;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a container with specified weight and type
     * Calculates transport cost based on type
     * @param weight Container weight
     * @param type Container type
     */
    public Container(int weight, ContainerType type) {
        this.id = generateId();
        this.location = -1;
        this.weight = weight;
        this.type = type;
        this.transportCost = calculateTransportCost();
    }

//============================
// METHODS
//============================

    /**
     * Generates unique container ID
     * Format: CONT0001, CONT0002, etc.
     * @return Generated container ID
     */
    private String generateId() {
        String id = String.format("CONT%04d", nextIdNumber);
        nextIdNumber++;
        return id;
    }

    /**
     * Calculates transport cost based on container type
     * Base cost: 1000
     * FREEZE and OPEN_TOP: +20% (1200)
     * @return Transport cost
     */
    private double calculateTransportCost() {
        double baseCost = 1000.0;
        
        if (type == ContainerType.FREEZE || type == ContainerType.OPEN_TOP) {
            return baseCost * 1.2;
        }
        
        return baseCost;
    }

    /**
     * Returns a string representation of the container
     * Format: ID LOCATION WEIGHT TYPE COST
     * @return Formatted string with container information
     */
    @Override
    public String toString() {
        return String.format("%-10s %-10d %-10d %-10s %-10.1f",
                id, location, weight, type.name(), transportCost);
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the container ID
     * @return Container ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the container location
     * @return Container location
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets the container location
     * @param location Location to set
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Gets the container weight
     * @return Container weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the container weight
     * @param weight Weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets the container type
     * @return Container type
     */
    public ContainerType getType() {
        return type;
    }

    /**
     * Sets the container type
     * @param type Type to set
     */
    public void setType(ContainerType type) {
        this.type = type;
    }

    /**
     * Gets the transport cost
     * @return Transport cost
     */
    public double getTransportCost() {
        return transportCost;
    }

    /**
     * Sets the transport cost
     * @param transportCost Cost to set
     */
    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    /**
     * Resets the container ID counter to 1
     * Useful for testing purposes
     */
    public static void resetIdCounter() {
        nextIdNumber = 1;
    }
}