package figure;

/**
 * Rectangle class
 * Represents a rectangle with base and height
 * Extends Figure class
 * @author Uriel Neves Silva
 */
public class Rectangle extends Figure {

    /* 
    ================
    ATTRIBUTES
    ================
    */
    private int base;
    private int height;
    private boolean isRounded;

    /*
    ================
    CONSTRUCTORS
    ================
    */

    /**
     * Constructor of the Rectangle class
     * All parameters are required
     * @param base Base of the rectangle
     * @param height Height of the rectangle
     * @param color Color of the rectangle
     * @param isFilled Whether the rectangle is filled or not
     * @param x X coordinate of the rectangle
     * @param y Y coordinate of the rectangle
     */
    public Rectangle(int x, int y, Color color, boolean isFilled, int base, int height ) {
        super(x, y, color, isFilled);
        this.base = base;
        this.height = height;
    }

    /**
     * Constructor of the Rectangle class
     * Only ints are required
     * @param base Base of the rectangle
     * @param height Height of the rectangle
     * @param x X coordinate of the rectangle
     * @param y Y coordinate of the rectangle
     */
    public Rectangle(int x, int y, int base, int height ) {
        super(x, y, Color.BLACK, false);
        this.base = base;
        this.height = height;
    }

    /*
    =================================
    METHODS
    =================================
    */

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }
   
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isRounded() {
        return isRounded;
    }
    
    public void setRounded(boolean rounded) {
        this.isRounded = rounded;
    }

    /**
     * The module is the distance between origin to the rectangle's corner
     * @return The module of the rectangle
     */
    @Override
    public double module() {
        return Math.round(Math.sqrt(Math.pow(base, 2) + Math.pow(height, 2)) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Rectangle [base=" + base + ", height=" + height + ", isRounded=" + isRounded + "]";
    }

    /**
     * Calculates the area of the rectangle
     * @return The area of the rectangle
     */
    public double area() {
        return base * height;
    }

    /**
     * Calculates the perimeter of the rectangle
     * @return The perimeter of the rectangle
     */
    public double perimetre() {
        return 2 * (base + height);
    }

}
