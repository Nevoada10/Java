package containerShip;

/**
 * ContainerShip.java
 * Class representing a container ship
 * Manages containers, weight, speed and capacity
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class ContainerShip {

//============================
// ATTRIBUTES
//============================

    private String name;
    private Container[] containers;
    private int numContainers;
    private int currentWeight;
    private float maxSpeed;
    private int maxWeight;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a container ship with specified capacity
     * @param name Ship name
     * @param maxSpeed Maximum speed in knots
     * @param maxWeight Maximum weight capacity
     * @param maxContainers Maximum number of containers
     */
    public ContainerShip(String name, float maxSpeed, int maxWeight, int maxContainers) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.maxWeight = maxWeight;
        this.containers = new Container[maxContainers];
        this.numContainers = 0;
        this.currentWeight = 0;
    }

//============================
// METHODS
//============================

    /**
     * Adds a container to the ship
     * Checks if there is space and weight capacity
     * Assigns location starting from 1
     * @param container Container to add
     * @return true if added successfully, false otherwise
     */
    public boolean addContainer(Container container) {
        // Check if there is space for the container
        if (numContainers >= containers.length) {
            return false;
        }
        
        // Check if adding this container would exceed max weight
        if (currentWeight + container.getWeight() > maxWeight) {
            return false;
        }
        
        // Add container and assign location
        containers[numContainers] = container;
        container.setLocation(numContainers + 1);
        numContainers++;
        currentWeight += container.getWeight();
        
        return true;
    }

    /**
     * Calculates total transport cost of all containers
     * @return Total transport cost
     */
    public double totalTransportCost() {
        double total = 0.0;
        
        for (int i = 0; i < numContainers; i++) {
            if (containers[i] != null) {
                total += containers[i].getTransportCost();
            }
        }
        
        return total;
    }

    /**
     * Orders containers by weight (ascending)
     * Only considers non-null containers
     * @return Array of containers sorted by weight
     */
    public Container[] orderContainers() {
        // Create a copy with only non-null containers
        Container[] sortedContainers = new Container[numContainers];
        for (int i = 0; i < numContainers; i++) {
            sortedContainers[i] = containers[i];
        }
        
        // Bubble sort by weight (ascending)
        for (int i = 0; i < sortedContainers.length - 1; i++) {
            for (int j = 0; j < sortedContainers.length - 1 - i; j++) {
                if (sortedContainers[j] != null && sortedContainers[j + 1] != null) {
                    if (sortedContainers[j].getWeight() > sortedContainers[j + 1].getWeight()) {
                        // Swap
                        Container temp = sortedContainers[j];
                        sortedContainers[j] = sortedContainers[j + 1];
                        sortedContainers[j + 1] = temp;
                    }
                }
            }
        }
        
        return sortedContainers;
    }

    /**
     * Returns a string representation of the ship
     * Shows ship data and all containers
     * @return Formatted string with ship information
     */
    @Override
    public String toString() {
        String result = "SHIP: " + name + "\n";
        result += "--------------------------------------------------\n";
        result += "CONTAINERS:\n";
        result += "--------------------------------------------------\n";
        result += String.format("%-10s %-10s %-10s %-10s %-10s\n",
                "ID", "LOCATION", "WEIGHT", "TYPE", "COST");
        
        // Show only non-null containers
        for (int i = 0; i < numContainers; i++) {
            if (containers[i] != null) {
                result += containers[i].toString() + "\n";
            }
        }
        
        result += "--------------------------------------------------\n";
        result += "NUM CONTAINERS: " + numContainers + "\n";
        result += "CURRENT WEIGHT: " + currentWeight + "\n";
        result += "MAX SPEED: " + maxSpeed + "\n";
        result += "MAX WEIGHT: " + maxWeight + "\n";
        
        return result;
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the ship name
     * @return Ship name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the ship name
     * @param name Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the containers array
     * @return Containers array
     */
    public Container[] getContainers() {
        return containers;
    }

    /**
     * Sets the containers array
     * @param containers Containers to set
     */
    public void setContainers(Container[] containers) {
        this.containers = containers;
    }

    /**
     * Gets the number of containers
     * @return Number of containers
     */
    public int getNumContainers() {
        return numContainers;
    }

    /**
     * Sets the number of containers
     * @param numContainers Number to set
     */
    public void setNumContainers(int numContainers) {
        this.numContainers = numContainers;
    }

    /**
     * Gets the current weight
     * @return Current weight
     */
    public int getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Sets the current weight
     * @param currentWeight Weight to set
     */
    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    /**
     * Gets the maximum speed in knots
     * @return Maximum speed
     */
    public float getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Sets the maximum speed
     * @param maxSpeed Speed to set
     */
    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Gets the maximum weight
     * @return Maximum weight
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * Sets the maximum weight
     * @param maxWeight Weight to set
     */
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}