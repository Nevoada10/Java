package vehicleInterface;
/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a subclass of vehicle
 */
public class Truck extends Vehicle implements Movable, Repairable, Refillable {

    //Attributes
    private int petrol;
    private int load;
   
    // Constructor
     public Truck(boolean available, int money, int posX, int posY, int petrol, int load) {
        super(available, money, posX, posY);
        this.petrol = petrol;
        this.load = load;
    }

    // Methods
    @Override
    public String toString() {
        String classExtra = " [petrol=" + petrol + ", load=" + load + "]";
        return super.toString() + classExtra;
    }

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
        if (getMoney() < Repairable.TRUCK) {
            return false;
        }
        
        // Else, pay the repair cost, and make the vehicle movable again,
        setMoney(getMoney() - Repairable.TRUCK);
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

        // If the vehicle doesn't have enough petrol, it will not move.
        if (petrol < Movable.TRUCK_CONSUM) {
            return false;
        }

        // Move the vehicle
        switch (axis) {
            case 'x' -> setPosX(getPosX() + Movable.TRUCK);
            case 'y' -> setPosY(getPosY() + Movable.TRUCK);
            default -> {
                // Invalid axis
                return false;
            }
        }
        
        // Decrease the petrol
        petrol -= Movable.TRUCK_CONSUM;
        return true;
    }

    /**
     * Refuels the vehicle if it is available and has enough money.
     * @return true if the vehicle was refueled, false otherwise
     */
    public boolean refill() {

        int tankCapacity = Refillable.TRUCK;

        // If the vehicle cannot move, you can't fuel it.
        if (isAvailable() == false) {
            return false;
        }
        
        // If the vehicle is already full, you can't fuel it
        if (petrol >= tankCapacity) {
            return false;
        }

        // Calculate the cost of refueling
        int littersNeeded = tankCapacity - petrol;
        int totalCost = littersNeeded * Refillable.PRICE;

        // If the vehicle doesn't have enough money, it cannot be refueled
        if(getMoney() < totalCost) {
            return false;
        }
        
        // Else, pay the cost and fill the tank
        setMoney(getMoney() - totalCost);
        petrol = tankCapacity;
        return true;
    }










}
