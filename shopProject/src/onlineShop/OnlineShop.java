package onlineShop;

import java.util.ArrayList;
import java.util.List;


public class OnlineShop {

    private List<Product> stock;

    public OnlineShop() {
        stock = new ArrayList<>();
    }

    public boolean addProduct(Product p) {
        return stock.add(p);
    }

    public boolean removeProduct(Product p) {
        return stock.remove(p);
    }

    // Vende un producto: le asigna un print y ajusta el precio final
    // Solo funciona si el producto está en venta (onSell = true)
    public boolean sellProduct(Product p, PrintImage printImage) {
        if (p.isOnSell()) {
            // Asignamos la imagen al producto
            p.setPrintImage(printImage);

            // Añadimos 10€ al precio actual por el coste de impresión
            p.setPrice(p.getPrice() + 10);

            // Quitamos el producto del stock (ya está vendido)
            return removeProduct(p);
        }

        return false;
    }


    public float stockValue() {
        float total = 0;
        for (Product p : stock) {
            total += p.getPrice();
        }
        return total;
    }

    // Devuelve el stock en formato texto con cabecera
    public String listStock() {
        String header = String.format("%-6s %-8s %-6s %-10s %-6s %-5s %-10s %-5s %-5s %-7s",
                "BARCODE", "DESC", "PRICE", "PRINT", "COLOR", "ON-SELL",
                "FABRIC", "POCKET?", "BUTTONS?", "CAP TYPE");
        String line = "-".repeat(80);

        StringBuilder sb = new StringBuilder();
        sb.append(header).append("\n");
        sb.append(line).append("\n");

        for (Product p : stock) {
            sb.append(p.toString()).append("\n");
        }

        return sb.toString();
    }

    public int numCaps() {
        int count = 0;
        for (Product p : stock) {
            if (p instanceof Cap) {
                count++;
            }
        }
        return count;
    }

    public int numTShirts() {
        int count = 0;
        for (Product p : stock) {
            if (p instanceof TShirt) {
                count++;
            }
        }
        return count;
    }

    // Devuelve la lista completa del stock (por si se necesita desde fuera)
    public List<Product> getStock() {
        return stock;
    }

    // Permite reemplazar toda la lista de stock
    public void setStock(List<Product> stock) {
        this.stock = stock;
    }
}