package onlineShop;

// Gorra: hereda directamente de Product (no es una samarreta)
public class Cap extends Product {

    private Visor visor;

    // Constructor: recibe barCode, desc, color y tipo de visera
    public Cap(String barCode, String desc, Color color, Visor visor) {
        super(barCode, desc, color);
        this.visor = visor;
        setPrice(price());
    }

    // Precio según el tipo de visera:
    // FLAT = 20€, CURVED = 30€
    @Override
    public float price() {
        if (visor == Visor.FLAT) {
            return 20;
        } else {
            return 30; // CURVED
        }
    }

    // --- Getter y Setter ---

    public Visor getVisor() {
        return visor;
    }

    public void setVisor(Visor visor) {
        this.visor = visor;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" %-10s %-7s", "", visor);
    }
}