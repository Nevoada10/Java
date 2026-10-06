package tryCatchPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatch {

    public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    int firstNumber = 0;
    int secondNumber = 0;
    boolean entradaCorrecta = false;

    // Demanar el primer número fins que sigui vàlid
    while (!entradaCorrecta) {
    try {
    System.out.print("Enter the first number: ");
    firstNumber = s.nextInt();
    entradaCorrecta = true;
    //InputMismatchException | NumberFormatException | NullPointerException e
    } catch (InputMismatchException | NumberFormatException | NullPointerException e) {
    System.out.println("Error: Cal introduir números enters.");
    s.nextLine(); // Netejar buffer per evitar bucle infinit
    }
    }

    entradaCorrecta = false; // Reiniciem per al segon número

    // Demanar el segon número fins que sigui vàlid
    while (!entradaCorrecta) {
    try {
    System.out.print("Enter the second number: ");
    secondNumber = s.nextInt();
    entradaCorrecta = true;
    //InputMismatchException | NumberFormatException | NullPointerException e
    } catch (InputMismatchException e) {
    System.out.println("Error: Cal introduir números enters.");
    s.nextLine(); // Neteja el buffer
    }
    }
   
    integersOperations(firstNumber, secondNumber);

    // NO tanquem Scanner perquè tancaria System.in
    }

    /**
* Aquest mètode ens dóna el resultat de múltiples operacions matemàtiques entre dos números enters
* @param fNumber és el primer número entrat per teclat per l'usuari
* @param sNumber és el segon número entrat per teclat per l'usuari
*/
	public static void integersOperations(int fNumber, int sNumber) {
	int suma = fNumber + sNumber;
	int resta = fNumber - sNumber;
	System.out.println("Add: " + suma);
	System.out.println("Subtract: " + resta);
	}
}
