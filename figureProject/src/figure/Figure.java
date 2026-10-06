package figure;

/**
 * Abstract class for figures (rectangle, oval, triangle)
 * @author  Uriel Neves Silva 
 */
public class Figure {

    /*
    ==================================
    ATTRIBUTES
    ==================================
    */
    private int x;                  // X coordinate of the figure in the canvas 
    private int y;                  // Y coordinate of the figure in the canvas
    private Color color;            // Color of the figure
    private boolean isFilled;       // Whether the figure is filled or not
    
    /*
    ==================================
    CONSTRUCTORS 
    ==================================
    */

    /**
     * Constructor 1 for the Figure class
     * Used when the figure has all attributes
     * @param x X coordinate of the figure
     * @param y Y coordinate of the figure
     * @param color Color of the figure
     * @param isFilled Whether the figure is filled or not
     */
    public Figure(int x, int y, Color color, boolean isFilled) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.isFilled = isFilled;
    }

    /**
     * Constructor 2 for the Figure class
     * Used when the figure has only x and y coordinates
     * @param x X coordinate of the figure
     * @param y Y coordinate of the figure
     */
    public Figure(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
        this.isFilled = false;
    }

    /*
    ==================================
    METHODS
    ==================================
    */
   
    /**
     * Returns a string representation of the figure
     * @return a string representation of the figure
     */
    @Override
    public String toString() {
        return "Position: (" + x + ", " + y + ")"
        + " | Color: "    + color
        + " | Filled: "   + isFilled;
        }

    /**
     * Resets the position of the figure to (0, 0)
     */
    public void resetPosition() {
        this.x = 0;
        this.y = 0;
    }
    
    /*
    ==================================
    METHODS
    ==================================
    */

    /**
     * Returns the X coordinate of the figure
     * A protected method to allow child classes to access it
     * But not other classes outside the package or subpackages
     * @return the X coordinate of the figure
     */
    protected int getX() {
        return x;
    }

    /**
     * Sets the X coordinate of the figure
     * A protected method to allow child classes to modify it
     * But not other classes outside the package or subpackages
     * @param x the X coordinate of the figure
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the Y coordinate of the figure
     * A public method to allow other classes to access it
     * @return the Y coordinate of the figure
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y coordinate of the figure
     * A public method to allow other classes to modify it
     * @param y the Y coordinate of the figure
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Calculates the module (distance from origin to position).
     * Formula: sqrt(x² + y²)
     * @return Module as a double
     */
    public double module() {
        return Math.sqrt(x * x + y * y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

}