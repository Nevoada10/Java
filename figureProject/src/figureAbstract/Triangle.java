package figureAbstract;

/**
 * This is a subclass of the Figure class
 * @author Uriel Neves Silva
 */
public class Triangle extends Figure{

    // Attributes
    int base;
    int height;

    // Constructors
    /**
     * This is the constructor of the Triangle class with all attributes
     * @param x x coordinate of the triangle
     * @param y y coordinate of the triangle
     * @param color color of the triangle
     * @param isFilled whether the triangle is filled or not
     * @param base base of the triangle
     * @param height height of the triangle
     */
    public Triangle(int x, int y, Color color, boolean isFilled, int base, int height) {
        super(x, y, color, isFilled);
        this.base = base;
        this.height = height;
    }

    /**
     * This is the constructor of the Triangle class with only the integer attributes
     * @param x x coordinate of the triangle
     * @param y y coordinate of the triangle
     * @param base base of the triangle
     * @param height height of the triangle
     */
    public Triangle(int x, int y, int base, int height) {
        super(x, y);
        this.base = base;
        this.height = height;
    }

    // Methods
    
    @Override
    public double area() {
        return (base * height) / 2;
    }

    @Override
    public double module() {
        return Math.sqrt(base * base + height * height);
    }

    /**
     * Calculates the perimeter of a non-scalene triangle
     * Uses the pythagorean theorem to calculate the left and right hypotenuse
     * @return the perimeter of the triangle
     */
    @Override
    public double perimetre() {
        double hipotenuseHalf = Math.sqrt((base/2.0) * (base/2.0) + height * height);
        return base + 2 * hipotenuseHalf;
    }

    @Override
    public String toString() {
        return "Triangle | " + super.toString()
             + " | Base: "   + base
             + " | Height: " + height;
    }
}
