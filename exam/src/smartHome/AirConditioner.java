package smartHome;

/**
 * @author Uriel Neves Silva
 *         Subclass of Appliance.
 */
public class AirConditioner extends Appliance {

    // Attributes
    private int targetTemperature;

    // Constructor
    public AirConditioner(String name, int consumptionW) {
        super(name, consumptionW);
        this.targetTemperature = 24; // It always use the value 24
    }

    // === Setter, own method ===
    public void setTemperature(int temp) {
        this.targetTemperature = temp;
    }

    /**
     * It was an abstract method in the Appliance class
     * Uses the formula given (consumptionW * hours) * ENERGY_PRICE / 1000.
     */
    @Override
    public double calculateDailyConsumption(int hours) {
        double cost = (consumptionW * hours) * ENERGY_PRICE / 1000.0;

        // If temperature below 22, it doubles the cost
        if (targetTemperature < 22) {
            cost *= 2;
        }

        return cost;
    }

    @Override
    public String toString() {
        return "Device: " + name + " [" + consumptionW + "W] (Air Conditioner, Target Temp: " + targetTemperature
                + "ºC)";
    }
}