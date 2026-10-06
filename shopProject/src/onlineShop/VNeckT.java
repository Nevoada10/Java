package onlineShop;

// Samarreta de cuello en V: puede tener bolsillo
public class VNeckT extends TShirt {

    private boolean hasPocket;

    // Constructor: recibe todos los atributos y calcula el precio
    public VNeckT(String barCode, String desc, Fabric fabric, Color color,
                  boolean hasPocket) {
        super(barCode, desc, fabric, color);
        this.hasPocket = hasPocket;
        setPrice(price());
    }

    // Precio base 11€ + tela + print (heredado) + bolsillo si tiene
    @Override
    public float price() {
        float total = 11;           // precio base VNeckT

        total += super.price();     // añade coste de tela (y print si tiene)

        if (hasPocket) total += 2;  // bolsillo: +2€

        return total;
    }

    // --- Getter y Setter ---

    public boolean isHasPocket() {
        return hasPocket;
    }

    public void setHasPocket(boolean hasPocket) {
        this.hasPocket = hasPocket;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" %-10s %-5s", getFabric(), hasPocket);
    }
}