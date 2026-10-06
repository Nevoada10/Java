package A009Client;

public class ClientTest {
    public static void main(String[] args) {
        System.out.println("=== TEST CONSTRUCTOR 1 ===");
        Client client1 = new Client("111111A", "John", "Doe", "ES", "666777888", "12345678A", (byte) 25);
        System.out.println(client1);
        
        System.out.println("\n=== TEST CONSTRUCTOR 2 ===");
        Client client2 = new Client("222222B", "Jane", "Smith", "87654321B");
        System.out.println(client2);
        
        System.out.println("\n=== TEST GETTERS ===");
        System.out.println("Account: " + client1.getAccount());
        System.out.println("Name: " + client1.getName());
        System.out.println("Surname: " + client1.getSurname());
        System.out.println("Nationality: " + client1.getNationality());
        System.out.println("Phone: " + client1.getPhone());
        System.out.println("DNI: " + client1.getDni());
        System.out.println("Age: " + client1.getAge());
        System.out.println("Debts: " + client1.getDebts());
        System.out.println("Active: " + client1.isActive());
        
        System.out.println("\n=== TEST SETTERS ===");
        client1.setAccount("999999Z");
        client1.setName("Johnny");
        client1.setSurname("Doe Jr");
        client1.setNationality("UK");
        client1.setPhone("111222333");
        client1.setDni("99999999Z");
        client1.setAge((byte) 30);
        client1.setDebts(500.50f);
        client1.setActive(false);
        System.out.println(client1);
        
        System.out.println("\n=== TEST INCREASE DEBTS ===");
        client1.setActive(true);
        client1.setDebts(0);
        System.out.println("Debts before: " + client1.getDebts());
        boolean increased = client1.increaseDebts(100.0f);
        System.out.println("Increase 100: " + increased);
        System.out.println("Debts after: " + client1.getDebts());
        
        System.out.println("\n=== TEST INCREASE DEBTS (inactive client) ===");
        client1.setActive(false);
        increased = client1.increaseDebts(50.0f);
        System.out.println("Increase 50 (inactive): " + increased);
        System.out.println("Debts: " + client1.getDebts());
        
        System.out.println("\n=== TEST INCREASE DEBTS (negative amount) ===");
        client1.setActive(true);
        increased = client1.increaseDebts(-20.0f);
        System.out.println("Increase -20: " + increased);
        System.out.println("Debts: " + client1.getDebts());
        
        System.out.println("\n=== TEST REDUCE DEBTS ===");
        client1.setDebts(200.0f);
        System.out.println("Debts before: " + client1.getDebts());
        boolean reduced = client1.reduceDebts(50.0f);
        System.out.println("Reduce 50: " + reduced);
        System.out.println("Debts after: " + client1.getDebts());
        
        System.out.println("\n=== TEST REDUCE DEBTS (more than available) ===");
        System.out.println("Debts before: " + client1.getDebts());
        reduced = client1.reduceDebts(300.0f);
        System.out.println("Reduce 300: " + reduced);
        System.out.println("Debts after: " + client1.getDebts());
        
        System.out.println("\n=== TEST REDUCE DEBTS (inactive client) ===");
        client1.setActive(false);
        client1.setDebts(100.0f);
        reduced = client1.reduceDebts(50.0f);
        System.out.println("Reduce 50 (inactive): " + reduced);
        System.out.println("Debts: " + client1.getDebts());
        
        System.out.println("\n=== TEST REDUCE DEBTS (negative amount) ===");
        client1.setActive(true);
        reduced = client1.reduceDebts(-30.0f);
        System.out.println("Reduce -30: " + reduced);
        System.out.println("Debts: " + client1.getDebts());
        
        System.out.println("\n=== TEST CANCEL DEBTS ===");
        client1.setDebts(250.0f);
        System.out.println("Debts before: " + client1.getDebts());
        boolean cancelled = client1.cancelDebts();
        System.out.println("Cancel debts: " + cancelled);
        System.out.println("Debts after: " + client1.getDebts());
        
        System.out.println("\n=== TEST CANCEL DEBTS (inactive client) ===");
        client1.setActive(false);
        client1.setDebts(150.0f);
        cancelled = client1.cancelDebts();
        System.out.println("Cancel debts (inactive): " + cancelled);
        System.out.println("Debts: " + client1.getDebts());
        
        System.out.println("\n=== TEST TOSTRING ===");
        Client client3 = new Client("222222C", "Anne", "Short", "UK", "333333", "45645645J", (byte) 30);
        System.out.println(client3);
    }
}
