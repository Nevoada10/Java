package smartHome;

/**
 * @author Uriel Neves Silva.
 *         Subclass of Appliance.
 */
public class WashingMachine extends Appliance {

    // Attributes
    private final int maxLoad;

    // Constructor
    public WashingMachine(String name, int consumptionW, int maxLoad) {
        super(name, consumptionW);
        this.maxLoad = maxLoad;
    }

    /**
     * Abstract method from Appliance
     * Uses the formula given: (consumptionW * hours)* ENERGY_PRICE / 100
     */
    @Override
    public double calculateDailyConsumption(int hours) {
        double cost = (consumptionW * hours) * ENERGY_PRICE / 1000.0;

        // If mx load greater than 8, it increases by 10% or * 1,10
        if (maxLoad > 8) {
            cost *= 1.10;
        }

        return cost;
    }

    @Override
    public String toString() {
        return "Device: " + name + " [" + consumptionW + "W] (Washing Machine, Max Load: " + maxLoad + "kg)";
    }
}