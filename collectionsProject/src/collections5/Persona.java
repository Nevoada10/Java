package collections5;

import java.util.Comparator;


/**
 * Persona.java
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

	@Override
	public int compareTo(Persona p) {
		return this.uid.compareTo(p.uid);
	}



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
