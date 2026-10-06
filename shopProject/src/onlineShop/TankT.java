package onlineShop;

// Samarreta de tirantes (tank top): sin bolsillo ni botones
public class TankT extends TShirt {

    // Constructor: recibe los atributos básicos y calcula el precio
    public TankT(String barCode, String desc, Fabric fabric, Color color) {
        super(barCode, desc, fabric, color);
        setPrice(price());
    }

    // Precio base 12€ + tela + print si tiene (heredado de TShirt)
    @Override
    public float price() {
        float total = 12;       // precio base TankT

        total += super.price(); // añade coste de tela (y print si tiene)

        return total;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" %-10s", getFabric());
    }
}