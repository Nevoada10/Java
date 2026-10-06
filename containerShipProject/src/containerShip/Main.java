package containerShip;

/**
 * Main.java
 * Test class for Container Ship system
 * Tests all classes and their functionality
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Main {
    public static void main(String[] args) {
        
        //============================
        // CREATE CONTAINERS
        //============================
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              CREATING 10 CONTAINERS                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        Container[] containers = new Container[10];
        containers[0] = new Container(1500, ContainerType.STANDARD);
        containers[1] = new Container(2000, ContainerType.FREEZE);
        containers[2] = new Container(2500, ContainerType.TANK);
        containers[3] = new Container(2200, ContainerType.OPEN_TOP);
        containers[4] = new Container(2100, ContainerType.STANDARD);
        containers[5] = new Container(1600, ContainerType.FREEZE);
        containers[6] = new Container(1800, ContainerType.TANK);
        containers[7] = new Container(2300, ContainerType.OPEN_TOP);
        containers[8] = new Container(2500, ContainerType.STANDARD);
        containers[9] = new Container(1500, ContainerType.FREEZE);
        
        //============================
        // SHOW ALL CONTAINERS
        //============================
        System.out.println("► Showing all containers:\n");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                "ID", "LOCATION", "WEIGHT", "TYPE", "COST");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < containers.length; i++) {
            System.out.println(containers[i]);
        }
        
        //============================
        // CREATE SHIP
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                 CREATING CONTAINER SHIP                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        ContainerShip ship = new ContainerShip("Ever Given", 25.0f, 15000, 10);
        System.out.println("► Ship created: " + ship.getName());
        System.out.println("  Max speed: " + ship.getMaxSpeed() + " knots");
        System.out.println("  Max weight: " + ship.getMaxWeight() + " kg");
        System.out.println("  Max containers: " + ship.getContainers().length);
        
        //============================
        // ADD CONTAINERS TO SHIP
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              ADDING CONTAINERS TO SHIP                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        for (int i = 0; i < containers.length; i++) {
            boolean added = ship.addContainer(containers[i]);
            
            if (added) {
                System.out.println("► Container " + containers[i].getId() + 
                                 " added successfully at location " + containers[i].getLocation());
                System.out.println("  Current ship weight: " + ship.getCurrentWeight() + " kg");
            } else {
                System.out.println("✗ Container " + containers[i].getId() + 
                                 " could NOT be added (no space or exceeds max weight)");
            }
        }
        
        //============================
        // SHOW SHIP DATA
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   SHIP INFORMATION                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        System.out.println(ship);
        
        //============================
        // CALCULATE TOTAL COST
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                TOTAL TRANSPORT COST                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        double totalCost = ship.totalTransportCost();
        System.out.println("► Total transport cost: " + totalCost + " €");
        
        //============================
        // ORDER CONTAINERS BY WEIGHT
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║          CONTAINERS ORDERED BY WEIGHT (ASCENDING)            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        Container[] orderedContainers = ship.orderContainers();
        
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                "ID", "LOCATION", "WEIGHT", "TYPE", "COST");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < orderedContainers.length; i++) {
            if (orderedContainers[i] != null) {
                System.out.println(orderedContainers[i]);
            }
        }
        
        //============================
        // CREATE ROUTE
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    CREATE ROUTE                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        Route route = new Route("Lisboa", "Buenos Aires", 9600, ship);
        
        System.out.println("► Route created:");
        System.out.println("  Origin: " + route.getOrigin());
        System.out.println("  Destination: " + route.getDestination());
        System.out.println("  Distance: " + route.getDistance() + " km");
        System.out.println("  Ship: " + route.getShip().getName());
        
        //============================
        // CALCULATE TRAVEL TIME
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                  CALCULATE TRAVEL TIME                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        double travelTime = route.getTime();
        System.out.println("► Travel time: " + travelTime + " hours");
        System.out.println("  (approximately " + (int)(travelTime / 24) + " days and " + 
            (int)(travelTime % 24) + " hours)");
        
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   ALL TESTS COMPLETED!                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}