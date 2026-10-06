package A008ProgModular.ticket;

/**
 * This Tax class provides methods for calculating tax and rounding decimal values.
 * 
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Tax {

// ======================================================================================================================
// METHODS
// ======================================================================================================================

    /**
     * This method takes the total price of a product (in euros) and the tax percentage of the product (in percent) and returns the price of the product without tax (in euros).
     * 
     * @param total The total price of the product, in euros.
     * @param tax The tax percentage of the product, in percent.
     * @return The price of the product without tax, in euros.
     */
    static float totalWithoutTax(float total, float tax) {
        float priceWithoutTax = total / ( 1 + (tax / 100) );
        priceWithoutTax = Calculations.roundDecimals(priceWithoutTax, 2); // Rounds to 2 decimals
        return priceWithoutTax;
    }


    /**
     * Calculates the tax of a product given its total price and the tax percentage.
     * 
     * @param total The total price of the product, in euros.
     * @param tax The tax percentage of the product, in percent.
     * @return The tax of the product, in euros.
     */
    static float calculateTax(float total, float tax) {
        float taxTotal = total - totalWithoutTax(total, tax);
        taxTotal = Calculations.roundDecimals(taxTotal, 2);
        return taxTotal;
    }

    
    /**
     * Rounds a float value to the specified number of decimal places.
     * 
     * @param value The float value to be rounded.
     * @param numberOfDecimalPlaces The number of decimal places to round to.
     * @return The rounded float value.
     */
    static float roundDecimals(float value, int numberOfDecimalPlaces) {
        return (float) (Math.round(value * Math.pow(10, numberOfDecimalPlaces)) / Math.pow(10, numberOfDecimalPlaces));
    }

} // End Class Tax
// END