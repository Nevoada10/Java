package collections4;

import java.util.*;

/**
 * MainTUI.java
 * Activitat 4: Collections
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
		java.util.List<Persona> llistaPersones = new java.util.ArrayList<>();
		llistaPersones.add(person1);
		llistaPersones.add(person2);
		llistaPersones.add(person3);
		llistaPersones.add(person4);
		
		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println(persona);
		}

		
		//(2)//////////////////////////////////////////////////////////////////////////////////////////

		// Ordenar la llista per uid  (Persona implementa comparator)
		// usar el mètode estàtic de Collections
		Collections.sort(llistaPersones, new Persona.ComparatorBY_UID());
		
		// iterar la llista amb un foreach i mostrar les persones
		

		for (Persona persona : llistaPersones) {
			System.out.println(persona);
		}
		
		//(3)//////////////////////////////////////////////////////////////////////////////////////////

		// Ordenar la llista per edat en ordre invers (Persona implementa comparator)
		// usar el mètode dinàmic de la llistaPersones
		llistaPersones.sort(new Persona.ComparatorBY_EDAT().reversed());

		
		// iterar la llista amb un foreach i mostrar les persones
		// TO DO
		System.out.println();
		for (Persona persona : llistaPersones) {
			System.out.println(persona);
		}

		//(4)//////////////////////////////////////////////////////////////////////////////////////////

		// Ordenar la llista per nom (Persona implementa comparator)
		// usar el mètode dinàmic de la llistaPersones
		llistaPersones.sort(new Persona.ComparatorBY_NOM());
		
		// iterar la llista amb un foreach i mostrar les persones
		System.out.println();
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

