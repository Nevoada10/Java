package vehicleInterface;
/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a subclass of vehicle
 */
public class Car extends Vehicle implements Movable, Repairable, Refillable {

    // Attributes
    private int petrol;

    // Constructor
    public Car(boolean available, int money, int posX, int posY, int petrol) {
        super(available, money, posX, posY);
        this.petrol = petrol;
    }

    // Methodsç

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
        if (getMoney() < Repairable.CAR) {
            return false;
        }
        
        // Else, pay the repair cost, and make the vehicle movable again,
        setMoney(getMoney() - Repairable.CAR);
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
        if (petrol < Movable.CAR_CONSUM) {
            return false;
        }

        // Move the vehicle
        if (axis == 'x') {
            setPosX(getPosX() + Movable.CAR);
        } 
        else if (axis == 'y') {
            setPosY(getPosY() + Movable.CAR);
        }
        else { // Invalid axis
            return false;
        }
        
        // Decrease the petrol
        petrol -= Movable.CAR_CONSUM;
        return true;
    }

    /**
     * Refuels the vehicle if it is available and has enough money.
     * @return true if the vehicle was refueled, false otherwise
     */
    public boolean refill() {

        int tankCapacity = Refillable.CAR;

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


    @Override
    public String toString() {
        String classExtra = " [petrol=" + petrol + "]";
        return super.toString() + classExtra;
    }

    public int getPetrol() {
    return petrol;
    }

    public void setPetrol(int petrol) {
        this.petrol = petrol;
    }
}
