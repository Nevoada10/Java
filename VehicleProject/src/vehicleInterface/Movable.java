package vehicleInterface;

/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This interface defines the movable behavior for vehicles
 */
public interface Movable {

    // DISPLACEMENT CONSTANTS
    public static final int CAR = 10;
    public static final int MOTO = 8;
    public static final int TRUCK = 7;
    public static final int BIKE = 3;

    // FUEL CONSUMPTION CONSTANTS
    public static final int CAR_CONSUM = 10;
    public static final int MOTO_CONSUM = 4;
    public static final int TRUCK_CONSUM = 6;

    // METHODS
    public boolean move(char axis);
    public boolean collision();
}
