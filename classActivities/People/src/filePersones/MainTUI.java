package filePersones;

/**
 * Main class for the filePersones package.
 * This class contains the main method to run the application.
 * @style: K.I.S.S (Keep It Simple, Stupid), Documentation Driven Development (DDD), Declarative Programming (DP)
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @since March 2026
 */
public class MainTUI{

	/**
	 * Main method to run the application.
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {

		// Exercise 1
		printBox("Creating a Persona");
		Persona person1 = new Persona("pere pou prat", "1000", "100", 25);
		System.out.println("Persona 1: " + person1);

		// Exercise 2
		printBox("Loading users from file ( populate() + toString() )");
		Users users = new Users();
		users.populate("./data/users.txt");
		System.out.println(users.toString());

		// Exercise 3
		printBox("Saving group to file.out");
		int num = users.saveGroup("100","./data/file.out");
		System.err.println("Count of users saved: " + num);

		// Exercise 4: Sorting by UID
		printBox("Sorting users by UID (descending)");
		users.ordenar("uid", false);
		System.out.println(users.toString());

		printBox("Sorting users by UID (ascending)");
		users.ordenar("uid", true);
		System.out.println(users.toString());
		
		// Exercise 4.1: Sorting by GID
		printBox("Sorting users by GID (descending)");
		users.ordenar("gid", false);
		System.out.println(users.toString());

		printBox("Sorting users by GID (ascending)");
		users.ordenar("gid", true);
		System.out.println(users.toString());
		
		// Exercise 4.2: Sorting by nom
		printBox("Sorting users by nom (descending)");
		users.ordenar("nom", false);
		System.out.println(users.toString());
		
		printBox("Sorting users by nom (ascending)");
		users.ordenar("nom", true);
		System.out.println(users.toString());

		// Exercise 4.3: Sorting by edat
		printBox("Sorting users by edat (descending)");
		users.ordenar("edat", false);
		System.out.println(users.toString());

		printBox("Sorting users by edat (ascending)");
		users.ordenar("edat", true);
		System.out.println(users.toString());
	}

	/*
	 * =========================
	 * HELPER METHODS
	 * =========================
	 */
	private static void printBox(String title) {
		int boxWidth    = 54;
		int paddingSize = (boxWidth - title.length()) / 2;

		String leftPad  = " ".repeat(paddingSize);
		String rightPad = " ".repeat(paddingSize);

		System.out.println("\n" + """
				╔══════════════════════════════════════════════════════════════╗
				  %s
				╚══════════════════════════════════════════════════════════════╝
				""".formatted(leftPad + title + rightPad));
	}
}