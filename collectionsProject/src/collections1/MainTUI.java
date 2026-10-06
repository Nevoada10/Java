package collections1;

import java.util.*;

// Main

// mkdir data
// ./data/users.txt
// pere pou prat:1000:10:18
// marta mas moreu:1001:100:25
// jan jing li:1002:10:21
// pau puig pujol:1003:100:20
// mac mec mic:1004:10:19


/**
 * Activity 1
 * MainTUI.java
 * This is a class that tests the ArrayList collection implementation
 * @author Uriel Neves Silva 
 */
public class MainTUI{
	public static void main(String[] args) {
		
		// crear 5 persones (person1..person5)
		Persona person1 = new Persona("Pere Pou Prat", "1000", "10", 18);
		Persona person2 = new Persona("Marta Mas Moreu", "1001", "100", 25);
		Persona person3 = new Persona("Jan Jing Li", "1002", "10", 21);
		Persona person4 = new Persona("Pau Puig Pujol", "1003", "100", 20);
		Persona person5 = new Persona("Mac Mec Mic", "1004", "10", 19);

		//(1)//////////////////////////////////////////////////////////////////////////////////////////
		System.out.println();
		// crear la llista i afegir les 4 primeres persones a la llista llistaPersones
		ArrayList<Persona> llistaPersones = new ArrayList<>();
		llistaPersones.add(person1);
		llistaPersones.add(person2);
		llistaPersones.add(person3);
		llistaPersones.add(person4);

		System.out.println("INITIAL LIST:" + llistaPersones);
		
		// iterar la llista amb un foreach (what is a foreach? It is a loop that iterates over a collection) i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println("PERSON " + persona + " at index " + llistaPersones.indexOf(persona));
		}
		System.out.println();
		
		// afegir la persona 5 a la posició 1 i a la última posició
		llistaPersones.add(1, person5);
		llistaPersones.add(person5);

		System.out.println("AFTER ADDING PERSON5: " + llistaPersones);
		
		// substituir el primer element per la persona 5
		llistaPersones.set(0, person5);
		
		System.out.println("AFTER SETTING FIRST ELEMENT TO PERSON5: " + llistaPersones);
		
		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println("PERSON " + persona + " at index " + llistaPersones.indexOf(persona));
		}
		System.out.println();

		//(2)//////////////////////////////////////////////////////////////////////////////////////////
		
		// mostrar el primer element
		System.out.println("FIRST ELEMENT:" + llistaPersones.get(0));

		// mostrar quants elements té la llista
		System.out.println("SIZE:" + llistaPersones.size());
		
		// Mostrar si la llista és buida o no
		System.out.println("IS EMPTY:" + llistaPersones.isEmpty());
		
		// Mostrar si la llista conté la primera persona "pere pou prat:1000:10:18"
		System.out.println("CONTAINS PERSON1:" + llistaPersones.contains(person1));
		System.out.println();	

		//(3)//////////////////////////////////////////////////////////////////////////////////////////
		
		// Assignar la llista a un Array de persones newLlista
		Persona[] newLlista = llistaPersones.toArray(new Persona[0]);
		System.out.println("ARRAY AFTER CONVERTING FROM LIST:" + Arrays.toString(newLlista));

		// mostrar l'array de persones amb un foreach
		for (Persona persona : newLlista) {
			System.out.println("PERSON " + persona);
		}
		// buidar la llista d'elements
		llistaPersones.clear();
		System.out.println("AFTER CLEARING LIST: " + llistaPersones);

		// mostrar quants elements té la llista = 0
		System.out.println("SIZE:" + llistaPersones.size());

		// mostrar si la llista és buida o no
		System.out.println("IS EMPTY:" + llistaPersones.isEmpty());
	
		// crear/assignar a la llista  llistaPersones a partir de l'Array de Persones newLlista
		System.out.println("NEW LLISTA BEFORE CREATING LIST FROM ARRAY: " + Arrays.toString(newLlista));
		llistaPersones = new ArrayList<>(Arrays.asList(newLlista));
		System.out.println("LLISTA PERSONES AFTER CREATING LIST FROM ARRAY: " + llistaPersones);

		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println("PERSONA: " + persona);
		}

		//(4)//////////////////////////////////////////////////////////////////////////////////////////

		// Atenció amb la creació de llistes mutables i inmutables.
		// Crea de nou llistaPersones usant l'Array NewLlista però mutable'
		llistaPersones = new ArrayList<>(Arrays.asList(newLlista));
		System.out.println("LLISTA PERSONES AFTER CREATING LIST FROM ARRAY: " + llistaPersones);

		// eliminar de la llista l'element de la persona 5 (la primera ocurrència)
		llistaPersones.remove(person5);
		System.out.println("LLISTA PERSONES AFTER REMOVING PERSON5: " + llistaPersones);

		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println("PERSONA: " + persona);
		}
		
		// Crear una nova llista llistaEsborrar amb les persones 5 i 3 i usar-la per 
		ArrayList<Persona> llistaEsborrar = new ArrayList<>();
		System.out.println("LLISTA ESBORRAR BEFORE ADDING PEOPLE: " + llistaEsborrar);
		llistaEsborrar.add(person5);
		llistaEsborrar.add(person3);
		System.out.println("LLISTA ESBORRAR AFTER ADDING PEOPLE: " + llistaEsborrar);

		// eliminar de la llista original les ocurrències de la nova llista
		llistaPersones.removeAll(llistaEsborrar);
		System.out.println("LLISTA PERSONES AFTER REMOVING ESBORRAR: " + llistaPersones);
		
		// iterar la llista amb un foreach i mostrar les persones
		for (Persona persona : llistaPersones) {
			System.out.println("PERSONA " + persona + " at index " + llistaPersones.indexOf(persona));
		}
				
	}
}
