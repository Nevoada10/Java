package figureAbstract;

/**
 * @author Uriel Neves Silva
 * Test class for the figureAbstract package
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
        
        // Figure is abstract class so it cannot be instantiated

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

        // area()
        System.out.println("Area rectangle: " + rectangle.area());
        System.out.println("Area rectangle2: " + rectangle2.area());

        // module()
        System.out.println("Module rectangle: " + rectangle.module());
        System.out.println("Module rectangle2: " + rectangle2.module());

        // perimetre
        System.out.println("Perimetre rectangle: " + rectangle.perimetre());
        System.out.println("Perimetre rectangle2: " + rectangle2.perimetre());

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
