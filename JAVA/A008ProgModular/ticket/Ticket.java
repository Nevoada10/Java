package A008ProgModular.ticket;

/**
 * This  Ticket  class represents a ticket for a coffee shop. It contains a private class  Product  that maps product codes to their names.
 * The  Ticket  class has methods for generating a ticket based on a 2D array of product codes, quantities, and prices.
 * The  generateTicket  method prints the ticket with the product details and calculates the total price, including tax.
 * The  total  method calculates the total price of all products in the ticket, and the  totalUnit  method calculates the total price of a single product.
 * The constants at the top of the class define the tax rate, the total price of the ticket, the Euro symbol, and a separator for the footer.
 * 
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Ticket {

// =================================================================
// CONSTANTS
// =================================================================
    
    private static final float TAX = 21f;
    private static final char EURO_SYMBOL = (char)8364;
    private static final String SEPARATOR = "-----------------------------------------------------";

// =================================================================
// PRIVATE CLASS
// =================================================================
    
/**
 * A private class that represents a product.
 * 
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 */
private class Product {

    /**
     * Given a product code on the array, returns the product name.
     * 
     * @param product The product code.
     * @return The product name.
     */
    private String product(float productCode) {
        int productName = (int) productCode;
        switch (productName) {
            case 1: return "Coffee large";
            case 2: return "Expresso";
            case 3: return "Coffee latte";
            case 4: return "Machiatto";
            case 5: return "Chocolate";
            case 6: return "Frappuccino";
            case 7: return "Coffee mocha";
            case 8: return "Americano";
            case 9: return "Brewed coffee";
            default: return "Unknown product";
        }
    }
} // End Private Class Product()

// =================================================================
// METHODS (TICKET CLASS)
// =================================================================

/**
 * Generates a ticket with the given products and their respective quantities and prices.
 * 
 * @param amounts A 2D array of floats where the first element of each subarray is the product code,
 * the second element is the quantity of the product and the third element is the price of the product.
 */
public void generateTicket(float[][] amounts) {

    float total = total(amounts);

    // Print the HEADER
    System.out.printf("""

Ticket Date: %s %s
-----------------------------------------------------
Product:            Units | Price | Total
-----------------------------------------------------
                """, Calculations.generateDate(), Calculations.generateTime()); // Static methods

     
    // Print from EACH PRODUCT (middle of the ticket): productName, productQuantity, productPrice and total
    for (int i = 0; i < amounts.length; i++) {

        // Array variables converted to explicit names
        Float productCode = amounts[i][0];
        Float productQuantity = amounts[i][1];
        Float productPrice = amounts[i][2];
        Float lineTotalPricePerProduct = totalUnit(amounts[i]);

        // Prints the product name using the Product private class
        String productName = new Product().product(productCode); // Instance method

        // Prints the name, quantity, price and total of each product
        System.out.printf("%-20s %5.0f %5.2f %7.2f%s\n", productName, // OBS: %m.n , m is the number of characters, n is the number of decimals.
        productQuantity, productPrice, lineTotalPricePerProduct, EURO_SYMBOL);
    } 

    // Print the FOOTER
    System.out.println(SEPARATOR); 
    System.out.printf("TAX:   %7.2f%s\n", Tax.calculateTax(total, TAX), EURO_SYMBOL); 
    System.out.println(SEPARATOR); 
    System.out.printf("TOTAL WITHOUT TAX: %7.2f%s\n" , Tax.totalWithoutTax(total, TAX), EURO_SYMBOL); 
    System.out.println(SEPARATOR); 
    System.out.printf("TOTAL: %7.2f%s\n\n", total(amounts), EURO_SYMBOL); 
    }


    /**
     * Calculates the total price of a given list of products.
     * 
     * @param amounts A 2D array of floats where the first element of each subarray is the product code,
     * the second element is the quantity of the product and the third element is the price of the product.
     * @return The total price of the products as a float.
     */
    private float total(float[][] amounts) {

        // Initial variables for the loop
        float productQuantity = 0f;
        float productPrice = 0f;
        float lineTotalPricePerProduct = 0f;
        float total = 0f;

        // A loop that calculates the total price of the ticket
        for (int i = 0; i < amounts.length; i++) {
            
            // Explicit variables
            productQuantity = amounts[i][1];
            productPrice = amounts[i][2];

            // Calculates and print the price per line
            lineTotalPricePerProduct = productQuantity * productPrice; 
            total += lineTotalPricePerProduct ; 
        }

        return total;
    }


    /**
     * Calculates the total price of a given product.
     * 
     * @param item A float array where the first element is the product code, the second element is the quantity of the product and the third element is the price of the product.
     * @return The total price of the product.
     */
    private float totalUnit(float[] item) {
        float quantity = item[1];
        float price = item[2];
        float total = quantity * price;

        return total;
    }


    
} // End Class Ticket
// END