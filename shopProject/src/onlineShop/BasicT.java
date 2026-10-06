package onlineShop;

// Samarreta básica: puede tener bolsillo y botones
public class BasicT extends TShirt {

    private boolean hasPocket;
    private boolean hasButtons;

    // Constructor
    public BasicT(String barCode, String desc, Fabric fabric, Color color,
                  boolean hasPocket, boolean hasButtons) {
        super(barCode, desc, fabric, color);
        this.hasPocket = hasPocket;
        this.hasButtons = hasButtons;

        // Calculate and store the price when creating the object
        setPrice(price());
    }

    // Base price 10€ + fabric + print (inherited from TShirt) + own extras
    @Override
    public float price() {
        float total = 10;           // base price

        total += super.price();     // add fabric cost (and print if it has)

        if (hasPocket) {
            total += 2;  
        }
        if (hasButtons) {
            total += 3; 
        }

        return total;
    }

    // --- Getters y Setters ---

    public boolean isHasPocket() {
        return hasPocket;
    }

    public void setHasPocket(boolean hasPocket) {
        this.hasPocket = hasPocket;
    }

    public boolean isHasButtons() {
        return hasButtons;
    }

    public void setHasButtons(boolean hasButtons) {
        this.hasButtons = hasButtons;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" %-10s %-5s %-5s",
                getFabric(), hasPocket, hasButtons);
    }
}