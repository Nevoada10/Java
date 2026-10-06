package collections4;

import java.util.Comparator;

/**
 * Persona.java
 * Activitat 4: Collections
 * @author Uriel Neves Silva
 */
public class Persona implements Comparable<Persona> {
	/*
	 * Attributes
	 */
	String nom;
	String uid;
	String gid;
	int edat;

	/*
	 * Constructors
	 */
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

	@Override
	public int compareTo(Persona other) {
		int thisUid = Integer.parseInt(this.uid);
		int otherUid = Integer.parseInt(other.uid);
		return Integer.compare(thisUid, otherUid);
	}

	public static class ComparatorBY_UID implements Comparator<Persona> {
    @Override
    public int compare(Persona a, Persona b) {
        return Integer.compare(Integer.parseInt(a.uid), Integer.parseInt(b.uid));
    }
	}

	public static class ComparatorBY_GID implements Comparator<Persona> {
		@Override
		public int compare(Persona a, Persona b) {
			return Integer.compare(Integer.parseInt(a.gid), Integer.parseInt(b.gid));
		}
	}

	public static class ComparatorBY_EDAT implements Comparator<Persona> {
		@Override
		public int compare(Persona a, Persona b) {
			return Integer.compare(a.edat, b.edat);
		}
	}

	public static class ComparatorBY_NOM implements Comparator<Persona> {
		@Override
		public int compare(Persona a, Persona b) {
			return a.nom.compareTo(b.nom);
		}
	}

	/* ============================
	 * Getters
	 * ============================ */
	public String getNom(){ return this.nom; }
	public String getUid(){ return this.uid; }
	public String getGid(){ return this.gid; }
	public int getEdat(){ return this.edat; }

}	

