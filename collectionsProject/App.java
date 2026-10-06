
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }


    /*
	 * =========================
	 * HELPER METHODS
	 * =========================
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
