package onlineShop;

// Clase abstracta intermedia: representa cualquier tipo de samarreta
// Hereda de Product y añade el atributo de tela (fabric)
public abstract class TShirt extends Product {

    private Fabric fabric;

    // Constructor: pasa barCode, desc y color a Product, y guarda el fabric
    public TShirt(String barCode, String desc, Fabric fabric, Color color) {
        super(barCode, desc, color);
        this.fabric = fabric;
    }

    // Calcula el precio base de la samarreta según la tela y si tiene print
    // Las subclases llaman a este método para completar su precio total
    @Override
    public float price() {
        float total = 0;

        // Si tiene una imagen impresa, se añaden 10€
        if (getPrintImage() != null) {
            total += 10;
        }

        // Se añade el coste extra según el tipo de tela
        total += fabric.getPrice();

        return total;
    }

    // --- Getter y Setter ---

    public Fabric getFabric() {
        return fabric;
    }

    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }
}