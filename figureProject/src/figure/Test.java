package figure;

/**
 * @author Uriel Neves Silva
 * Test class for the figure package
 */
public class Test {
    public static void main(String[] args) {

        /* -------------------------------------------------------------------------- */
        /*                                    Color                                   */
        /* -------------------------------------------------------------------------- */

        printBox("Color enum");

        System.out.println(Color.CYAN);
        System.out.println(Color.YELLOW);
        System.out.println(Color.MAGENTA);
        System.out.println(Color.BLACK);

        /* -------------------------------------------------------------------------- */
        /*                                    Figure                                  */
        /* -------------------------------------------------------------------------- */
        
        printBox("Figure");

        // Constructor with all parameters
        Figure figure = new Figure(0, 0, null, false);

        // Constructor with only integer attributes
        Figure figure2 = new Figure(10, 10, Color.BLACK, true);

        // to String
        System.out.println(figure.toString());
        System.out.println(figure2.toString());

        // resetPosition()
        figure2.resetPosition();

        // setX()
        figure2.setX(50);
        System.out.println(figure2.toString());

        // setY()
        figure2.setY(50);
        System.out.println(figure2.toString());

        // setColor()
        figure2.setColor(Color.MAGENTA);
        System.out.println(figure2.toString());

        // setFilled()
        figure2.setFilled(false);
        System.out.println(figure2.toString());

        // getX()
        System.out.println("X: " + figure2.getX());

        // getY()
        System.out.println("Y: " + figure2.getY());

        // getColor()
        System.out.println("Color: " + figure2.getColor());

        // isFilled()
        System.out.println("Filled: " + figure2.isFilled());

        // toString(): after all changes
        System.out.println(figure2.toString());

        /* -------------------------------------------------------------------------- */
        /*                                    Rectangle                               */
        /* -------------------------------------------------------------------------- */

        printBox("Rectangle");

        // Constructor with all parameters
        Rectangle rectangle = new Rectangle(0, 0, Color.BLACK, true, 10, 20);
        
        // Constructor with only integer attributes
        Rectangle rectangle2 = new Rectangle(25, 25, 20, 40);

        //Test methods: to String
        System.out.println(rectangle.toString());
        System.out.println(rectangle2.toString());

        //setBase()
        rectangle.setBase(15);
        System.out.println(rectangle.toString());

        //setHeight()
        rectangle.setHeight(25);
        System.out.println(rectangle.toString());

        //setRounded()
        rectangle.setRounded(true);
        System.out.println(rectangle.toString());

        // GetBase()
        System.out.println("Base: " + rectangle.getBase());

        // GetHeight()
        System.out.println("Height: " + rectangle.getHeight());

        // isRounded()
        System.out.println("Rounded: " + rectangle.isRounded());

        // area()
        System.out.println("Area rectangle: " + rectangle.area());
        System.out.println("Area rectangle2: " + rectangle2.area());

        // module()
        System.out.println("Module rectangle: " + rectangle.module());
        System.out.println("Module rectangle2: " + rectangle2.module());

        // perimetre
        System.out.println("Perimetre rectangle: " + rectangle.perimetre());
        System.out.println("Perimetre rectangle2: " + rectangle2.perimetre());

        // toString(): after changes
        System.out.println(rectangle.toString());
        System.out.println(rectangle2.toString());

        /* -------------------------------------------------------------------------- */
        /*                                    Square                                  */
        /* -------------------------------------------------------------------------- */

        printBox("Square");

        // Constructor with all parameters
        Square square = new Square(0, 0, Color.BLACK, true, 10);
        
        //Constructor with only integer attributes
        Square square2 = new Square(25, 25, 12);

        System.out.println("Area square: " + square.area());
        System.out.println("Area square2: " + square2.area());

        // module()
        System.out.println("Module square: " + square.module());
        System.out.println("Module square2: " + square2.module());

        // perimetre
        System.out.println("Perimetre square: " + square.perimetre());
        System.out.println("Perimetre square2: " + square2.perimetre());

        //Test one super method: getBase()
        System.out.println("Base: " + square.getBase());
        System.out.println("Base: " + square2.getBase());
        
        /* -------------------------------------------------------------------------- */
        /*                                    Oval                                    */
        /* -------------------------------------------------------------------------- */

        printBox("Oval");

        // Constructor with all parameters
        Oval oval = new Oval(0, 0, Color.BLACK, true, 1, 1);
        
        // Constructor with only integer attributes
        Oval oval2 = new Oval(10, 20, 25, 25);

        //Test methods: to String
        System.out.println(oval.toString());
        System.out.println(oval2.toString());

        // area()
        System.out.println("Area oval: " + oval.area());
        System.out.println("Area oval2: " + oval2.area());

        // module()
        System.out.println("Module oval: " + oval.module());
        System.out.println("Module oval2: " + oval2.module());

        // perimetre
        System.out.println("Perimetre oval: " + oval.perimetre());
        System.out.println("Perimetre oval2: " + oval2.perimetre());

        //setRadius1()
        oval.setRadius1(2);
        oval2.setRadius1(30);

        //setRadius2()
        oval.setRadius2(2);
        oval2.setRadius2(30);

        //getRadius1()
        System.out.println("Radius1: " + oval.getRadius1());
        System.out.println("Radius1: " + oval2.getRadius1());

        //getRadius2()
        System.out.println("Radius2: " + oval.getRadius2());
        System.out.println("Radius2: " + oval2.getRadius2());

        // toString(): after changes
        System.out.println(oval.toString());
        System.out.println(oval2.toString());

        /* -------------------------------------------------------------------------- */
        /*                                    Cercle                                  */
        /* -------------------------------------------------------------------------- */

        printBox("Cercle");

        // Constructor with all parameters
        Cercle cercle = new Cercle(0, 0, Color.BLACK, true, 1);
        
        // Constructor with only integer attributes
        Cercle cercle2 = new Cercle(10, 20, 25);

        //Test methods: to String
        System.out.println(cercle.toString());
        System.out.println(cercle2.toString());

        // area()
        System.out.println("Area cercle: " + cercle.area());
        System.out.println("Area cercle2: " + cercle2.area());

        // module()
        System.out.println("Module cercle: " + cercle.module());
        System.out.println("Module cercle2: " + cercle2.module());

        // perimetre
        System.out.println("Perimetre cercle: " + cercle.perimetre());
        System.out.println("Perimetre cercle2: " + cercle2.perimetre());
        
        /* -------------------------------------------------------------------------- */
        /*                                   Triangle                                 */
        /* -------------------------------------------------------------------------- */

        printBox("Triangle");

    
        // Constructor with all parameters
        Triangle triangle = new Triangle(0, 0, Color.BLACK, true, 1, 2);
        
        // Constructor with only integer attributes
        Triangle triangle2 = new Triangle(10, 20, 6, 4);

        //Test methods: to String
        System.out.println(triangle.toString());
        System.out.println(triangle2.toString());

        // area()
        System.out.println("Area triangle: " + triangle.area());
        System.out.println("Area triangle2: " + triangle2.area());

        // module()
        System.out.println("Module triangle: " + triangle.module());
        System.out.println("Module triangle2: " + triangle2.module());

        // perimetre
        System.out.println("Perimetre triangle: " + triangle.perimetre());
        System.out.println("Perimetre triangle2: " + triangle2.perimetre());

      }   




    /**
     * Prints a formatted box with the given title
     * @param title The title to display in the box
     */
    	private static void printBox(String title) {
		int boxWidth    = 54;
		int paddingSize = Math.max(0, (boxWidth - title.length()) / 2);

		String leftPad  = " ".repeat(paddingSize);
		String rightPad = " ".repeat(paddingSize);

		System.out.println("\n" + """
				╔══════════════════════════════════════════════════════════════╗
				  %s
				╚══════════════════════════════════════════════════════════════╝
				""".formatted(leftPad + title + rightPad));
	}

}
