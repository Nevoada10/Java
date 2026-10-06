package collections5;

import java.util.*;

/**
 * MainTUI.java
 * Activitat 5: Comparació d'objectes i strings
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
				
		printBox("1: Create the list and iterate it");
		// afegir les 4 primeres persones a la llista llistaPersones
		List<Persona> llistaPersones = new ArrayList<>();
		llistaPersones.add(person1);
		llistaPersones.add(person2);
		llistaPersones.add(person3);
		llistaPersones.add(person4);
		
		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona: llistaPersones) {
			System.out.println(persona);
		}
		
		//(2)//////////////////////////////////////////////////////////////////////////////////////////

		printBox("2: Compare objects");
		// assigna a newPerson1 la referència de person1
		Persona newPerson1 = person1;
		
		// mostra si són iguals (true/false)
		System.out.println(newPerson1 == person1);
		
		// crea newPerson2 amb les mateixes dades que person2
		Persona newPerson2 = new Persona("marta mas moreu", "1001", "100", 25);

		// mostra si són iguals (true/false)
		System.out.println(newPerson2 == person2);

		/*
		Why?
		In the first case, both variables point to the same object in the string pool.
		In the second case, a new object is created in the heap, so the reference is different.
		*/
				
		//(3)//////////////////////////////////////////////////////////////////////////////////////////
		
		printBox("3: Compare strings");
		//crea un string str1 amb "pere pou prat"
		String str1 = "pere pou prat";
		
		//crea un string str2 amb "pere pou prat"
		String str2 = "pere pou prat";

		// mostra si són iguals (true/false)
		System.out.println( str1 == str2 ); // R: True

		// crea un string str3 amb new amb "pere pou prat"
		String str3 = new String("pere pou prat"); 

		// mostra si són iguals stra1 i str3  (true/false)
		System.out.println( str1 == str3 ); // R: False
		
		/*
		Why?
		In the first case, both variables point to the same object in the string pool.
		In the second case, a new object is created in the heap, so the reference is different.
		*/
	
		//(4)//////////////////////////////////////////////////////////////////////////////////////////

		// mostra aplicar equals a str1 i str3
		System.out.println(str1.equals(str3));
		
		/*
		Why?
		The equals method compares the content of the strings, not the references.
		*/
		
		//(5)//////////////////////////////////////////////////////////////////////////////////////////
		
		printBox("5: Compare objects with equals");
		// mostra aplicar equals a newPerson2 i person2
		System.out.println(newPerson2.equals(person2));
		
		/* 
		Why false?
		Because the equals method in the Persona class compares the references, not the content.
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

