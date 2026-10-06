package collections2;
// Persona

//package listPersones


public class Persona {
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
}
