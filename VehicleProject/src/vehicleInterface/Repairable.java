package vehicleInterface;

/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This interface defines the repairable behavior for vehicles
 */
public interface Repairable {

    // REPAIR COST CONSTANTS 
    int CAR   = 500;
    int MOTO  = 300;
    int TRUCK = 400;
    int BIKE  = 50;

    // METHODS
    boolean repair();
}