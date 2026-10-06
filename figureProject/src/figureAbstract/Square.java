package figureAbstract;

/**
 * @author Uriel Neves Silva
 * Square class is a subclass of Rectangle
 */
public class Square extends Rectangle {

    public Square(int x, int y, Color color, boolean isFilled, int side) {
        super(x, y, color, isFilled, side, side);
    }
    /**
     * Reduced constructor using only int parameters
     * @param side the side of the square
     * @param x the x coordinate of the square
     * @param y the y coordinate of the square
     */
    public Square(int x, int y, int side) {
        super(x, y,side, side);
    }

}
