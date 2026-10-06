package collections3;

import java.util.*;
/**
 * Activity 3
 * MainTUI.java
 * This is a class that tests the ArrayList collection implementation
 * @codestyle explicit names, clear variable names, meaningful method names
 * @author Uriel Neves Silva 
 */
public class MainTUI{
	public static void main(String[] args) {
		
		// crear 5 persones (person1..person5)
		Persona person1 = new Persona("pere pou prat", "1000", "10", 18);
		Persona person2 = new Persona("marta mas moreu", "1001", "100", 25);
		Persona person3 = new Persona("jan jing li", "1002", "10", 21);
		Persona person4 = new Persona("pau puig pujol", "1003", "100", 20);
		Persona person5 = new Persona("mac mec mic", "1004", "10", 19);

		//(1)//////////////////////////////////////////////////////////////////////////////////////////

		printBox("1) Llista de persones");
		// afegir les 4 primeres persones a la llista llistaPersones
		List<Persona> llistaPersones = new ArrayList<>();
		llistaPersones.add(person1);
		llistaPersones.add(person2);
		llistaPersones.add(person3);
		llistaPersones.add(person4);
		
		// iterar la llista amb un foreach i mostrar les persones+

		for (Persona persona : llistaPersones) {
			System.out.println(persona);
		}

		System.out.println();
		
		//(2)//////////////////////////////////////////////////////////////////////////////////////////

		// Ordenar la llista pel criteri de comparació natural (UID) en ordre invers
		// (Persona implementa comparable)
		// Usar el mètode estàtic de Collection: 
		// pista: Collections.reverseOrder()
		
		printBox("2) List of people in reverse order");
		Collections.sort(llistaPersones, Collections.reverseOrder());

		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println(persona);
		}
		
		//(3)//////////////////////////////////////////////////////////////////////////////////////////

		// Ordenar la llista pel criteri de comparació natural (UID) en ordre invers
		// (Persona implementa comparable)
		// usar el mètode que implementen les llistes (les collection)
		// llistaPersones.sort()
		printBox("3) List of people in reverse order (using list.sort())");
		llistaPersones.sort(Collections.reverseOrder());

		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println(persona);
		}
			
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

