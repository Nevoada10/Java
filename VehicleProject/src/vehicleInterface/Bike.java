package vehicleInterface;
/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a subclass of vehicle
 */
public class Bike extends Vehicle implements Movable, Repairable {

    // Constructor
    public Bike(boolean available, int money, int posX, int posY) {
        super(available, money, posX, posY);
    }

    // Methods

    /**
     * Repairs the vehicle if it is not available and has enough money.
     * @return true if the vehicle was repaired, false otherwise
     */
    public boolean repair() {

        // If the vehicle is on move, it cannot be repaired
        if(isAvailable() == true) {
            return false;
        }

        // If the vehicle doesn't have enough money, it cannot be repaired
        if (getMoney() < Repairable.BIKE) {
            return false;
        }
        
        // Else, pay the repair cost, and make the vehicle movable again,
        setMoney(getMoney() - Repairable.BIKE);
        setAvailable(true);
        return true; // Repair successful
    }

    /**
     * Moves the vehicle in the specified axis.
     * @param axis the axis to move the vehicle ('x' or 'y')
     * @return true if the vehicle was moved, false otherwise
     */
    public boolean move(char axis) {
        // If the vehicle is not avaialable to move, it will not.
        if (isAvailable() == false) {
            return false;
        }

        // Move the vehicle
        switch (axis) {
            case 'x' -> setPosX(getPosX() + Movable.BIKE);
            case 'y' -> setPosY(getPosY() + Movable.BIKE);
            default -> {
                // Invalid axis
                return false;
            }
        }
        
        return true;
    }
}
