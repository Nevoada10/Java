package collections6;

import java.util.*;

/**
 * Persona.java
 * Activity 6
 * @author Uriel Neves Silva
 */
public class Persona implements Comparable<Persona> {
	String nom;
	String uid;
	String gid;
	int edat;

	public Persona(String nom, String uid, String gid, int edat){
		this.nom = nom;
		this.uid = uid;
		this.gid = gid;
		this.edat = edat;
	}

	public Persona(String linia){
		String [] parts = linia.split(":");
		String nom = parts[0];
		String uid = parts[1];
 		String gid = parts[2];
 		int edat = Integer.parseInt(parts[3]);
		this.nom = nom;
		this.uid = uid;
		this.gid = gid;
		this.edat = edat;
	}

	public String getNom(){
		return this.nom;
	}

	public String getUid(){
		return this.uid;
	}

	public String getGid(){
		return this.gid;
	}

	public int getEdat(){
		return this.edat;
	}

	public String toString(){
		return String.format("%s(%s/%s) %d anys", nom, uid, gid, edat);
	}

	// redefinir compareTo
	@Override
	public int compareTo(Persona p) {
		return this.uid.compareTo(p.uid);
	}

		/**
	 * equals: two Persona objects are considered equal if ALL their fields match.
	 *
	 * CONTRACT — equals and hashCode must always agree:
	 *   If a.equals(b) == true  ->  a.hashCode() == b.hashCode()  (mandatory)
	 *   If a.hashCode() == b.hashCode()  ->  a.equals(b) may still be false (allowed, called a collision)
	 *
	 * The standard equals pattern always follows these four steps:
	 *   1. Same reference?  -> trivially equal, return true immediately.
	 *   2. Is obj null or a different class? -> can't be equal, return false.
	 *   3. Cast safely to our type.
	 *   4. Compare every field we care about.
	 */
	@Override
	public boolean equals(Object obj) {
		// Step 1: same memory address -> same object, definitely equal
		if (this == obj) return true;
 
		// Step 2: null or wrong type -> not equal
		if (obj == null || getClass() != obj.getClass()) return false;
 
		// Step 3: safe cast — we know it's a Persona now
		Persona other = (Persona) obj;
 
		// Step 4: compare every field
		//   Strings -> .equals()   |   int -> ==
		return this.uid.equals(other.uid)
			&& this.gid.equals(other.gid)
			&& this.nom.equals(other.nom)
			&& this.edat == other.edat;
	}

	/**
	 * hashCode: produces an integer "fingerprint" of the object.
	 *
	 * Objects.hash() is a convenience method that combines multiple fields
	 * into one hash value using a prime-number formula internally.
	 * We must include exactly the same fields we used in equals() —
	 * otherwise the equals/hashCode contract would be broken.
	 *
	 * Why does this matter in practice?
	 *   HashMap, HashSet, Hashtable all use hashCode() first to find the bucket,
	 *   then equals() to confirm. If hashCode() is wrong, those collections break.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nom, uid, gid, edat);
	}


	// -----------------------------------------------------------------------
	// Comparators as static inner classes
	// Static inner class = belongs to the Persona class, not to any instance.
	// This keeps sorting logic grouped with the class it sorts.
	// -----------------------------------------------------------------------

	// Comparators com a subclasses: atenció ha de ser ordenació estable
	public static class ComparatorBY_UID implements Comparator<Persona> {
        	@Override
	        public int compare(Persona p1, Persona p2) {
        		return p1.getUid().compareTo(p2.getUid());
        	}
    	}

	public static class ComparatorBY_GID implements Comparator<Persona> {
        	@Override
	        public int compare(Persona p1, Persona p2) {
        		return p1.getGid().compareTo(p2.getGid());
        	}
    	}

        public static class ComparatorBY_EDAT implements Comparator<Persona> {
                @Override
                public int compare(Persona p1, Persona p2) {
                        return Integer.compare(p1.getEdat(), p2.getEdat());
                }
        }

	public static class ComparatorBY_NOM implements Comparator<Persona> {
                @Override
                public int compare(Persona p1, Persona p2) {
                        return p1.getNom().compareTo(p2.getNom());
                }
        }	
}
