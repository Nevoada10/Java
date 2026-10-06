package vehicleInterface;
/**
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is the superclass Vehicle
 */
public class Vehicle {

    // Attributes
    private int posX;
    private int posY;
    private int money;
    private boolean available;  // Determines if the vehicle can move or not

    // Constructor
    public Vehicle(boolean available, int money, int posX, int posY) {
        this.available = available;
        this.money = money;
        this.posX = posX;
        this.posY = posY;
    }

    // Methods
    @Override
    public String toString() {
        return "Vehicle [posX=" + posX + ", posY=" + posY + ", money=" + money + ", available=" + available
                + ", getClass()=" + getClass() + "]";
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Simulates a collision, changes the available status to false if the state is true
     * @return true if the vehicle was available and collided, false otherwise
     */
    public boolean collision(){
        if (available == false) {
            return true;
        }
        available = false;
        return true;
    }

 


    

}
 


    
   
     

