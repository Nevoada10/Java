package collections6;

import java.util.*;

/**
 * MainTUI.java
 * Activity 6
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
				

		printBox("1) Adding the 4 first people to the list");
		// afegir les 4 primeres persones a la llista llistaPersones
		List<Persona> llistaPersones = new ArrayList<>();
		llistaPersones.add(person1);
		llistaPersones.add(person2);
		llistaPersones.add(person3);
		llistaPersones.add(person4);
		
		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println(persona);
		}
		
		//(2)//////////////////////////////////////////////////////////////////////////////////////////

		printBox("2) Comparing references and creating new objects");
		// assigna a newPerson1 la referència de person1
		Persona newPerson1 = person1;
		
		// mostra si són iguals (true/false)
		System.out.println(newPerson1 == person1);
		
		// crea newPerson2 amb les mateixes dades que person2
		Persona newPerson2 = new Persona(person2.getNom(), person2.getUid(), person2.getGid(), person2.getEdat());

		// mostra si són iguals (true/false)
		System.out.println(newPerson2 == person2);
		
		//(3)//////////////////////////////////////////////////////////////////////////////////////////
		
		printBox("3) Applying equals to newPerson2 and person2");
		// mostra aplicar equals a newPerson2 i person2
		System.out.println(newPerson2.equals(person2)); // true

		/*
		Why true?
		Because both objects have the same content (nom, uid, gid, edat)
		 */
		
		//(4)//////////////////////////////////////////////////////////////////////////////////////////
		
		printBox("4) Hashcode comparison");
		// mostrar el hashcode de newPerson2 
		System.out.println("Hashcode de newPerson2: " + newPerson2.hashCode());
	
		
		// mostrar el hashcode de person2
		System.out.println("Hashcode de person2: " + person2.hashCode());

		
		//(5)//////////////////////////////////////////////////////////////////////////////////////////
		
		printBox("5) Hashcode comparison result");
		// Mostra si la comparació del hascode és igual (true/false)
		System.out.println(newPerson2.hashCode() == person2.hashCode()); // true

		/*
		Why true?
		Because both hashcodes are identical integers
		It's a comparison between two integers
		 */
		
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

