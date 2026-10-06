package smartHome;

import java.util.ArrayList;

/* 
Preguntes Teòriques (1 pt extra)

a) No, funcionaria porque Appliance es una clase abstracta y no puede instanciar objetos.

b) la estructura es la ArrayList inventory, diferente de una array normal que solo almacena un tipo, la Array list puede almacenar todos los
subtipos de Appliance que existen. El beneficício es que todos los hijos de la clase appliance pueden ser almacenados y accesados desde el mismo lugar.
De lo contrario sería necesario crear una Array para cada subtipo/tipo específico. Línea 23
*/

/**
 * @author Uriel Neves Silva
 *         Main class given by the teacher, not modified
 */
public class SmartHomeApp {
    public static void main(String[] args) {

        // Creació de la llista (ArrayList d'Appliance)
        ArrayList<Appliance> inventory = new ArrayList<>();

        // Instanciació de les subclasses
        WashingMachine wm = new WashingMachine("LG ThinQ", 2000, 9);
        AirConditioner ac = new AirConditioner("Fujitsu Eco", 1500);

        // Configurem una temperatura que activi la condició de consum extra
        ac.setTemperature(20);

        // Afegir a la llista
        inventory.add(wm);
        inventory.add(ac);

        System.out.println("=== SMART HOME MANAGEMENT SYSTEM ===");
        System.out.println("Global Energy Price: " + Connectable.ENERGY_PRICE + " EUR/kWh");
        System.out.println("------------------------------------");

        // Bucle per processar tots els elements de la llista
        for (Appliance app : inventory) {
            // Mostrem el toString() sobreescrit
            System.out.println(app.toString());

            // Calculem el cost econòmic per a 5 hores d'ús
            double cost = app.calculateDailyConsumption(5);
            System.out.printf("Estimated cost for 5 hours: %.2f EUR %n", cost);

            // Provem mètodes de la interfície i mètode final de la classe abstracta
            app.updateFirmware();
            app.togglePower();

            System.out.println("------------------------------------");
        }
    }
}