package edt09_abstract_role;
public class Main {
    public static void main(String[] args) {

        /* 
         * Instaiate a Role object as an Outlander
         *
         * Role role = new Outlander(0.0f, 0.0f);
         * System.out.println(role.toString());
         * 
         * However, you cannoy instantiate a Role object directly, as it is an abstract class.
         */

        // Create an Outlander
        printBox("OUTLANDER");
        Outlander outlander = new Outlander(0.0f, 0.0f);
        System.out.println(outlander.toString());

        // Move and jump with the Outlander
        outlander.move();
        outlander.jump();
        System.out.println("After moving and jumping");
        System.out.println(outlander.toString());

        //Create a Soldier
        printBox("SOLDIER");
        Soldier soldier = new Soldier(0.0f, 0.0f);
        System.out.println(soldier.toString());

        // Move and jump with the Soldier
        soldier.move();
        soldier.jump();
        System.out.println("After moving and jumping");
        System.out.println(soldier.toString());

        // Create a Constructor
        printBox("CONSTRUCTOR");
        Constructor constructor = new Constructor(0.0f, 0.0f);
        System.out.println(constructor.toString());

        // Move and jump with the Constructor
        constructor.move();
        constructor.jump();
        System.out.println("After moving and jumping");
        System.out.println(constructor.toString());

        // Create a Ninja
        printBox("NINJA");
        Ninja ninja = new Ninja(0.0f, 0.0f);
        System.out.println(ninja.toString());

        // Move and jump with the Ninja
        ninja.move();
        ninja.jump();
        System.out.println("After moving and jumping");
        System.out.println(ninja.toString());

        // Using the Ninja to test other methods
        ninja.extraMove();
        System.out.println("After extra move (20,5 units on X)");
        System.out.println(ninja.toString());

        ninja.extraJump();
        System.out.println("After extra jump (25,2 units on Y)");
        System.out.println(ninja.toString());

        ninja.setLifes((byte) 1);
        System.out.println("After setting lifes to 1");
        System.out.println(ninja.toString());
        
        ninja.decreaseLife();
        System.out.println("After decreasing life");
        System.out.println(ninja.toString());
        
        // Try to decrease life again (should not work because lifes is already 0)
        ninja.decreaseLife();
        System.out.println("After decreasing life again (Won't work because lifes is already 0)");
        System.out.println(ninja.toString());

        // Increase life
        ninja.increaseLife();
        System.out.println("After increasing life");
        System.out.println(ninja.toString());

        ninja.setLifes((byte) 3);
        System.out.println("After setting lifes to 3");
        System.out.println(ninja.toString());

        // Try to increase life again (should not work now because lifes is already 3)
        ninja.increaseLife();
        System.out.println("After increasing life again (Won't work because lifes is already 3)");
        System.out.println(ninja.toString());

        // Set available to false
        ninja.setAvailable(false);
        System.out.println("After setting available to false");
        System.out.println(ninja.toString());
        
        // Try to use extraMove when not available (should not work)
        ninja.extraMove();
        System.out.println("After trying extra move when not available (Won't work)");
        System.out.println(ninja.toString());
        
        // Reset role
        ninja.resetRole();
        System.out.println("After reset");
        System.out.println(ninja.toString());
        System.out.println();

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
