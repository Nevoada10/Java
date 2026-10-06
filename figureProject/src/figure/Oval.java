package figure;

/**
 * This is a subclass of the figure class that represents an oval figure
 * @author Uriel Neves Silva
 */
public class Oval extends Figure {

    /*
    ==================================
    ATTRIBUTES
    ==================================
    */
   private int radius1;
   private int radius2;

    /*
    ==================================
    CONSTRUCTORS
    ==================================
    */

    /**
     * Constructor with all parameters
     * @param radius1 the first radius of the oval
     * @param radius2 the second radius of the oval
     * @param color the color of the oval
     * @param isFilled whether the oval is filled or not
     * @param x the x coordinate of the oval
     * @param y the y coordinate of the oval
     * @param y
     */
    public Oval(int x, int y, Color color, boolean isFilled, int radius1, int radius2) {
        super(x, y, color, isFilled);
        this.radius1 = radius1;
        this.radius2 = radius2;
    }

    /** Constructor using only int parameters
     * 
     */
    public Oval(int radius1, int radius2, int x, int y) {
        super(x, y, Color.BLACK, false);
        this.radius1 = radius1;
        this.radius2 = radius2;
    }

    /*
    ==================================
    METHODS
    ==================================
    */

    /**
     * The module is the distance between the center and the edge of the oval
     * @return the module of the oval
     */
    @Override
    public double module() {
        return Math.sqrt(Math.pow(radius1, 2) + Math.pow(radius2, 2));
    }

    /**
     * To calculate the area of an Ellipse 
     * Formula: π * a * b
     * where a is the semi-major axis and b is the semi-minor axis
     */
    public double area() {
        return Math.PI * radius1 * radius2;
    }

    /**
     * The perimetre is the perimeter of the ellipse
     * Formula: 2 * π * sqrt((radius1^2 + radius2^2) / 2)
     * where radius1 is the semi-major axis and radius2 is the semi-minor axis
     * @return the perimeter of the ellipse
     */
    public double perimetre() {
        double a = radius1; // semi-major axis
        double b = radius2; // semi-minor axis
        return 2 * Math.PI * Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)) / 2);
    }

    @Override
    public String toString() {
        return "Oval | " + super.toString()
             + " | Radius1: " + radius1
             + " | Radius2: " + radius2;
    }

    public int getRadius1() {
        return radius1;
    }

    public void setRadius1(int radius1) {
        this.radius1 = radius1;
    }

    public int getRadius2() {
        return radius2;
    }
    
    public void setRadius2(int radius2) {
        this.radius2 = radius2;
    }
}
