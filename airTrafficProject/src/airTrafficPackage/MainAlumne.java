/*
* Main.java 2 Feb 2025
*
*
* © Copyright 2025 Joan Sèculi <jseculi@escoladeltreball.org>
*
* This is free software, licensed under the GNU General Public License v3.
* See http://www.gnu.org/licenses/gpl.html for more information.
*/
package airTrafficPackage;



public class MainAlumne {
	public static void main(String[] args) {
		// 1. Models
		System.out.println("\nCreate 2 Models: ");
		Model airbusA320 = new Model("AIRBUS-A320", 10, 3500.0);
		Model boeing787 = new Model("BOEING-787", 10, 8000.0);

		// Show models
		System.out.println("\nShow models: ");
		

		// 2. Airports
		System.out.println("\nCreate 5 Airports: ");
		Airport barcelona = new Airport(Code.BCN);
		Airport madrid = new Airport(Code.MAD);
		Airport newYork = new Airport(Code.JFK);
		Airport london = new Airport(Code.LHR);
		Airport tokyo = new Airport(Code.HKD);

		System.out.println("\nShow airports: ");
		

		// 3. Passengers
		System.out.println("\nCreate Passengers: ");
		Passenger passengerA1 = new Passenger("John", "Doe", "A11111111", true);
		Passenger passengerA2 = new Passenger("Jane", "Smith", "B22222222", false);
		Passenger passengerA3 = new Passenger("Carlos", "Silva", "C33333333", true);
		Passenger passengerA4 = new Passenger("Peter", "King", "D44444444", false);
		Passenger passengerA5 = new Passenger("Aura", "Queen", "E55555555", true);
		Passenger passengerA6 = new Passenger("Mona", "Lisa", "F66666666", false);
		Passenger passengerA7 = new Passenger("Marian", "Tool", "G77777777", true);
		Passenger passengerA8 = new Passenger("Lola", "Viva", "H88888888", false);
		Passenger passengerA9 = new Passenger("Jules", "Verne", "I99999999", true);
		Passenger passengerA10 = new Passenger("Lady", "Gaga", "J12121212", false);
		Passenger passengerA11 = new Passenger("Victoria", "Beckham", "K13131313", true);
		Passenger passengerA12 = new Passenger("Kyle", "Blue", "L14141414", false);
		Passenger passengerA13 = new Passenger("David", "Milan", "A11111111", true);
		Passenger passengerA14 = new Passenger("John", "Lloyd", "a11111111", false);

		System.out.println("\nShow passengerA1: ");
		

		// 4. Flights
		System.out.println("\nCreate flight Barcelona - Madrid at 08:45: ");
		Flight flight1 = new Flight("IB123", barcelona, madrid, airbusA320, "08:45");

		System.out.println("\nShow flight: ");

		
		System.out.println("\nCreate flight Barcelona - New York at 10:55");
		Flight flight11 = new Flight("IB152", barcelona, newYork, boeing787, "10:55");

		System.out.println("\nShow flight");
		

		System.out.println("\nCreate flight Barcelona - London at 14:30");
		Flight flight12 = new Flight("BA205", barcelona, london, airbusA320, "14:30");

		System.out.println("\nShow flight");
		

		System.out.println("\nCreate flight Barcelona - London at 16:25");
		Flight flight13 = new Flight("BA206", barcelona, london, airbusA320, "16:25");

		System.out.println("\nShow flight");
		

		System.out.println("\nCreate flight Barcelona - Tokyo at 18:40");
		Flight flight14 = new Flight("AA332", barcelona, tokyo, boeing787, "18:40");

		System.out.println("\nShow flight");
		

		System.out.println("\nCreate flight Madrid - Barcelona at 21:55");
		Flight flight21 = new Flight("AA101", madrid, barcelona, airbusA320, "21:15");

		System.out.println("\nShow flight");
		

		System.out.println("\nCreate flight London - Barcelona at 21:30");
		Flight flight22 = new Flight("AA122", london, barcelona, airbusA320, "21:30");

		System.out.println("\nShow flight");
		

		System.out.println("\nAdd passenger passengerA1 to flight Barcelona Madrid at 08:45: ");
		
		System.out.println("\nAdd the same passenger passengerA1 to flight: ");
		

		System.out.println("\nAdd passenger passengerA13 with same dni to flight: ");
		

		System.out.println("\nAdd passenger passengerA14 with same dni to flight: ");
		

		System.out.println("\nAdd all passengers (from A2 to A12) to flight (only 10 should be added): ");

		

		System.out.println("\nList flight passengers:");
		

		System.out.println("\nSort flight Passengers by lastName:");
		

		System.out.println("\nSearch Passengers with text = \"a\" in flight");
		

		

		
		System.out.println("\nSearch Passengers with text = \"VA\" in flight");
		

		
		System.out.println("\nCount passenger with baggage");
		

		
		System.out.println("\nCount passenger without baggage");
		

		System.out.println("\nAdd departing flights from Barcelona airpor to Madrid, New York and Tokyo");
		System.out.println("flght1, flight11, flight12, flight13, flight14");
	

		System.out.println("\nAdd arriving flights to Barcelona from Madrid and London");
		System.out.println("flght21, flight22");
		

		System.out.println("\nShow flights departing from Barcelona:");
		

		System.out.println("\nShow flights arriving to Barcelona:");
		

		System.out.println("\nSearch flights in Barcelona Airport that go to London or arrive from London");
		

	}
}