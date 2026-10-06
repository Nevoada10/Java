package vehicleInterface;

/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This interface defines the refillable behavior for vehicles
 */
public interface Refillable {

    // TANK CAPACITY CONSTANTS 
    int CAR   = 100;
    int MOTO  = 50;
    int TRUCK = 200;

    // FUEL PRICE CONSTANT 
    int PRICE = 1;

    // METHODS
    boolean refill();
}