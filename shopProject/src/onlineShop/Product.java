package onlineShop;

// Clase abstracta: define la estructura común de todos los productos
public abstract class Product {

    // Atributos comunes a todos los productos
    private String barCode;
    private String desc;
    private float price = 0;
    private PrintImage printImage = null;
    private Color color;
    private boolean onSell = true;

    // Constructor: recibe código de barras, descripción y color
    public Product(String barCode, String desc, Color color) {
        this.barCode = barCode;
        this.desc = desc;
        this.color = color;
    }

    // Método abstracto: cada subclase debe implementar cómo calcular su precio
    public abstract float price();

    // --- Getters y Setters ---

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public PrintImage getPrintImage() {
        return printImage;
    }

    public void setPrintImage(PrintImage printImage) {
        this.printImage = printImage;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
    }

    // Representación en texto del producto (columnas base)
    @Override
    public String toString() {
        // Muestra NULL si no tiene print asignado
        String print = (printImage == null) ? "NULL" : printImage.name();
        return String.format("%-6s %-8s %-6.2f %-10s %-6s %-5s",
                barCode, desc, price, print, color, onSell);
    }
}