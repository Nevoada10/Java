package collections3;

/**
 * Persona.java
 * Activity 3 - Persona class
 * This class represents a person with name, uid, gid and age
 * @author Uriel Neves Silva
 * 
 * @interface Comparable<Persona>, this interface allows us to compare Personas by their uid
 * Comparable is a generic interface in Java for defining natural ordering of objects.
 * It enables instances of implementing classes to be compared to others of the same type, primarily for sorting and ordered collections.
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

	public String toString(){
		return String.format("%s(%s/%s) %d anys", nom, uid, gid, edat);
	}

	/**
	 * This method exists to compare two Personas by their uid
	 * It is required by the Comparable interface
	 */
	@Override
	public int compareTo(Persona other) {
		int thisUid = Integer.parseInt(this.uid);
		int otherUid = Integer.parseInt(other.uid);
		return Integer.compare(thisUid, otherUid);
	}
/* ============================================================ */
/* GETTERS */
/* ============================================================ */

	public String getNom(){ return this.nom; }
	public String getUid(){ return this.uid;}
	public String getGid(){ return this.gid;}
	public int getEdat(){ return this.edat; }
}
