package onlineShop;

// Samarreta de manga larga: puede tener bolsillo
public class LongT extends TShirt {

    private boolean hasPocket;

    // Constructor: recibe todos los atributos y calcula el precio
    public LongT(String barCode, String desc, Fabric fabric, Color color,
                 boolean hasPocket) {
        super(barCode, desc, fabric, color);
        this.hasPocket = hasPocket;
        setPrice(price());
    }

    // Precio base 12€ + tela + print (heredado) + bolsillo si tiene
    @Override
    public float price() {
        float total = 12;           // precio base LongT

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