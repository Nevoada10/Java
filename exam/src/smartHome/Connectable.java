package smartHome;

/**
 * @author Uriel Neves Silva
 *         Interface used for appliances and descendants.
 */
public interface Connectable {

    int MAX_SENSORS = 5;
    double ENERGY_PRICE = 0.15;

    // === Contract methods of the interface ===
    boolean connect(String wifiPassword);

    void updateFirmware();
}