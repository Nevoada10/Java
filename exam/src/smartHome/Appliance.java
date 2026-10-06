package smartHome;

/**
 * @author Uriel Neves Silva
 *         Abstract class, implements interfce connectable ( connect() and
 *         updateFirmare() )
 */
public abstract class Appliance implements Connectable {

    // Attributes
    protected String name;
    protected int consumptionW;
    protected boolean isOn;

    // Constructor
    /**
     * Requirements: name consumption, isOn (default false)
     */
    public Appliance(String name, int consumptionW) {
        this.name = name;
        this.consumptionW = consumptionW;
        this.isOn = false;
    }

    /**
     * Returns true if the password is not null and greater than 5
     */
    @Override
    public boolean connect(String wifiPassword) {
        return wifiPassword != null && wifiPassword.length() > 5;
    }

    /**
     * It prints the desired format according to the name of the appliance
     */
    @Override
    public void updateFirmware() {
        System.out.println("Updating firmware for " + name + "...");
    }

    // === Abstract method ===
    public abstract double calculateDailyConsumption(int hours);

    public final void togglePower() {
        isOn = !isOn; // Switches it on and off.
        System.out.println(name + " is now " + (isOn ? "ON" : "OFF"));
    }

    // === Getters === (if necessary in the next codes)
    public String getName() {
        return name;
    }

    public int getConsumptionW() {
        return consumptionW;
    }
}