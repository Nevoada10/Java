package filePersones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Users.java
 * Class managing an array of users (Personas)
 * Provides functionality to load from file, sort, and filter by GID
 * @style: K.I.S.S (Keep It Simple, Stupid), Documentation Driven Development (DDD), Declarative Programming (DP)
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 * @since March 2026
 */
public class Users {

    /*
    ======================================
    ATTRIBUTES
    ======================================
    */
    private static final int MAXUSERS = 5;
    Persona[] users;
    int numUsers = 0;

    /*
    ======================================
    COMPARATORS
    ======================================

    A Comparator<T> is an object that knows how to compare two objects of type T.
    It has one method: compare(T a, T b)
        - Returns negative  → a comes before b
        - Returns zero      → a and b are equal
        - Returns positive  → a comes after b

    Comparator.comparing(keyExtractor) is a factory method that builds a Comparator
    from a method reference. For example:
        Comparator.comparing(Persona::getNom)
    is equivalent to writing:
        (a, b) -> a.getNom().compareTo(b.getNom())

    For int fields (like edat), we use Comparator.comparingInt() to avoid autoboxing.
    For String fields parsed as numbers (uid, gid), we use a lambda with Integer.parseInt.
    */

    /** Sorts by nom alphabetically */
    private static final Comparator<Persona> BY_NOM =
        Comparator.comparing(Persona::getNom);

    /** Sorts by UID numerically (uid is stored as String, so we parse it) */
    private static final Comparator<Persona> BY_UID =
        Comparator.comparingInt(p -> Integer.parseInt(p.getUid()));

    /** Sorts by GID numerically (gid is stored as String, so we parse it) */
    private static final Comparator<Persona> BY_GID =
        Comparator.comparingInt(p -> Integer.parseInt(p.getGid()));

    /** Sorts by edat numerically (edat is already an int) */
    private static final Comparator<Persona> BY_EDAT =
        Comparator.comparingInt(Persona::getEdat);

    /*
    ======================================
    CONSTRUCTORS
    ======================================
    */

    /**
     * Constructor for the Users class
     */
    public Users() {
        this.numUsers = 0;
        this.users = new Persona[MAXUSERS];
    }

    /*
    ======================================
    METHODS
    ======================================
    */

    /**
     * Populates the users array from a text file.
     * Each line represents one person with format: nom:uid:gid:edat
     * @param fileIn the name of the file to read
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public void populate(String fileIn) {
        int counter = 0;
        try {
            FileReader fr = new FileReader(fileIn);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                users[counter] = new Persona(line);
                counter++;
                line = br.readLine();
            }
            numUsers = counter;

            fr.close();
            br.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Error reading file");
        } catch (NumberFormatException e) {
            System.err.println("Error parsing file, invalid number format");
        }
    }

    /**
     * Sorts the populated users array using a Comparator.
     *
     * HOW IT WORKS:
     *   1. We pick one of the four Comparator constants defined above based on `field`.
     *   2. If descending order is requested, we call .reversed() on the comparator —
     *      this wraps it and flips the sign of compare(), no extra code needed.
     *   3. Arrays.sort(array, fromIndex, toIndex, comparator) sorts only the slice
     *      [0, numUsers), so the null slots at the end are never touched.
     *      This avoids a NullPointerException that would happen if we sorted the
     *      full array including empty positions.
     *
     * WHY NOT Collections.sort?
     *   Collections.sort works on List, not arrays. Converting with Arrays.asList()
     *   creates a fixed-size view of the FULL array (including nulls), which crashes.
     *   Arrays.sort with a range is cleaner and safer here.
     *
     * @param field     "nom" | "uid" | "gid" | "edat"  (case-insensitive)
     * @param ascending true → A→Z or low→high | false → Z→A or high→low
     */
    public void ordenar(String field, boolean ascending) {

        // Step 1: choose the base comparator for the requested field
        Comparator<Persona> comparator = switch (field.toLowerCase()) {
            case "uid"  -> BY_UID;
            case "gid"  -> BY_GID;
            case "edat" -> BY_EDAT;
            default     -> BY_NOM;  // "nom" or any unrecognised value
        };

        // Step 2: reverse if descending order was requested
        if (!ascending) {
            comparator = comparator.reversed();
        }

        // Step 3: sort only the populated slice [0, numUsers) — null slots untouched
        Arrays.sort(users, 0, numUsers, comparator);
    }

    /**
     * Returns a string representation of the Users array.
     * Format: "nº nom cognoms (UID/GID) edat anys"
     * Example: 1 Pere Pou Prat (1000/100) 18 anys
     *
     * @return formatted string of all users
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < numUsers; i++) {
            result += (i + 1) + " " + users[i].toString() + "\n";
        }
        return result;
    }

    /**
     * Writes all users matching the given GID to an output file.
     * @param gid     the GID to filter by
     * @param fileOut the output file path
     * @return the number of matching users written
     */
    public int saveGroup(String gid, String fileOut) {
        int count = 0;
        try {
            FileWriter fw = new FileWriter(fileOut);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < numUsers; i++) {
                /*
                 * .equals() compares object contents (String value).
                 * == compares memory addresses — never use it for String comparison.
                 */
                if (users[i].getGid().equals(gid)) {
                    count++;
                    bw.write(users[i].toString() + "\n");
                    System.out.println("Saved user: " + users[i]); // DEBUG
                }
            }

            bw.close();
            fw.close();

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }
}