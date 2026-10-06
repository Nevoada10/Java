package figure;

/**
 * Cercle class is a subclass of Oval
 */
public class Cercle extends Oval {
   
    /**
     * Constructor with only int parameters
     * @param x the x coordinate of the circle
     * @param y the y coordinate of the circle
     * @param radius the radius of the circle
     */
    public Cercle(int x, int y, int radius) {
        super(radius, radius, x, y);
    }

    /**
     * Constructor with all parameters
     * @param x the x coordinate of the circle
     * @param y the y coordinate of the circle
     * @param color the color of the circle
     * @param isFilled whether the circle is filled or not
     * @param radius the radius of the circle
     */
    public Cercle(int x, int y, Color color, Boolean isFilled, int radius) {
        super(x, y, color, isFilled, radius, radius);
    }
}
