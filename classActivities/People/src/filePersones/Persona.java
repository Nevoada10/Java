package filePersones;

/**
 * Persona.java
 * Class representing a person with name, UID, GID and age
 * Can be created from individual fields or parsed from a TXT line 
 * Implements Comparable interface to compare Personas based on UID
 * 
 * What Comparable does
 * Comparable is an interface that forces a class to answer the question:
 * "How do I compare myself to another object of my same type?"
 * When you write implements Comparable<Persona>, Java requires you to define compareTo(). That method is the rule that any sorting algorithm will use when it needs to decide the order between two Persona objects.
 * 
 * @style: K.I.S.S (Keep It Simple, Stupid), Documentation Driven Development (DDD), Declarative Programming (DP)
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 * @since March 2026
 */
public class Persona implements Comparable<Persona> {
	String nom;
	String uid;
	String gid;
	int edat;

    /*
    ========================================================================
    CONSTRUCTORS
    ======================================================================== 
    */

	/**
     * Constructor with all parameters
     * @param nom Person name
     * @param uid User ID
     * @param gid Group ID
     * @param edat Age
     */
	public Persona(String nom, String uid, String gid, int edat) {
		this.nom = nom;
        this.uid = uid;
        this.gid = gid;
        this.edat = edat;
	}

	/**
     * Constructor from TXT line
     * Parses a line with format: "nom cognoms:1000:100:15"
     * @param linia TXT line to parse
     */
	public Persona(String linia) {
        
        // Declare the array of size 4
        String[] parts = new String[4];
        
        // Split the line by ":" and store the result in the array
		parts = linia.split(":");
        
        // Assign the values to the fields
        this.nom = parts[0];
        this.uid = parts[1];
        this.gid = parts[2];
        this.edat = Integer.parseInt(parts[3]); // It means we are converting the string to an int 

        /*
        OBS: Parsing is different from casting: 
            Parsing: Converting a STRING to a NUMBER, To transform unstructured data into a structured format that software can easily manipulate.
            Casting: Converting a NUMBER to a STRING, 
        */
	}

    /*
    ========================================================================
    METHODS
    ======================================================================== 
    */

    /**
     * Compares this Persona with another Persona based on their UID
     * @param other the Persona to compare with
     * @return a negative integer, zero, or a positive integer as this Persona is less than, equal to, or greater than the specified Persona
     */
    @Override
    public int compareTo(Persona other) {
        int thisUid = Integer.parseInt(this.uid);      // It converts the uid to an int of the current person
        int otherUid = Integer.parseInt(other.uid);     // It converts the uid to an int of the other person
        return Integer.compare(thisUid, otherUid); 
    }

    /**
	 * To string of the class Persona
	 * Overridden method to return a formatted string
	 * @return a string with the format "nom cognoms(uid/gid) anys anys"
	 */
	@Override
	public String toString(){
		return String.format("%s (%s/%s) %d anys", this.nom, this.uid, this.gid, this.edat);
	}
    
    /*
    ========================================================================
    GETTERS
    ======================================================================== 
    */

    public String getNom() { return nom; } // Nom is the name
    public String getUid() { return uid; } // Uid is the user id
    public String getGid() { return gid; } // Gid is the group id
	public int getEdat() { return edat; } // Edat is the age
}