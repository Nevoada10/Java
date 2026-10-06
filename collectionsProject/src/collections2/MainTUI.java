package collections2;

import java.util.*;

/**
 * Activity 2
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
				
		// afegir les 4 primeres persones a la llista llistaPersones
		printBox("Add the first 4 people to the list");
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

		// iterar la llista "iterador" amb un Iterator mostrar els elements si no són l'element persona 2
		printBox("Iterate and show elements that are not person 2");
		
		Iterator<Persona> iterador = llistaPersones.iterator();
		while (iterador.hasNext()) {
			Persona persona = iterador.next();
			if (!persona.equals(person2)) {
				System.out.println(persona);
			}
		}


		//(3)//////////////////////////////////////////////////////////////////////////////////////////
	
		// afegir la persona 5 a la primera posició, a la següent i a la última
		printBox("Add person 5 to the list at positions 0, 1 and last");
		llistaPersones.add(0, person5);
		llistaPersones.add(1, person5);
		llistaPersones.add(llistaPersones.size(), person5);
		
		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println(persona);
		}


		//(4)//////////////////////////////////////////////////////////////////////////////////////////
		
		// iterar la llista amb un Iterator de nom "nouIterador" i eliminar tots els elements que siguin
		// iguals a persona 5
		
		printBox("Remove person 5 from the list");
		llistaPersones.remove(person5);

		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println(persona);
		}

		System.out.println();
			
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

