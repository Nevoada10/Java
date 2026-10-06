package containerShip;

/**
 * Route.java
 * Class representing a shipping route
 * Calculates travel time based on distance and ship speed
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Route {

//============================
// ATTRIBUTES
//============================

    private String origin;
    private String destination;
    private float distance;
    private ContainerShip ship;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a route with origin, destination, distance and ship
     * @param origin Origin port
     * @param destination Destination port
     * @param distance Distance in km
     * @param ship Container ship
     */
    public Route(String origin, String destination, float distance, ContainerShip ship) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.ship = ship;
    }

//============================
// METHODS
//============================

    /**
     * Calculates travel time in hours
     * Converts ship speed from knots to km/h (1 knot = 1.852 km/h)
     * Formula: time = distance / speed
     * @return Travel time in hours
     */
    public double getTime() {
        // Convert knots to km/h: 1 knot = 1.852 km/h
        double speedKmH = ship.getMaxSpeed() * 1.852;
        
        // Calculate time: distance / speed
        double time = distance / speedKmH;
        
        return time;
    }

    /**
     * Returns a string representation of the route
     * @return Formatted string with route information
     */
    @Override
    public String toString() {
        return "Route{" +
               "origin='" + origin + '\'' +
               ", destination='" + destination + '\'' +
               ", distance=" + distance +
               ", ship=" + ship.getName() +
               ", time=" + getTime() + " hours" +
               '}';
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the origin port
     * @return Origin port
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin port
     * @param origin Origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Gets the destination port
     * @return Destination port
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination port
     * @param destination Destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Gets the distance
     * @return Distance in km
     */
    public float getDistance() {
        return distance;
    }

    /**
     * Sets the distance
     * @param distance Distance to set
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * Gets the container ship
     * @return Container ship
     */
    public ContainerShip getShip() {
        return ship;
    }

    /**
     * Sets the container ship
     * @param ship Ship to set
     */
    public void setShip(ContainerShip ship) {
        this.ship = ship;
    }
}