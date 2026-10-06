package onlineShop;

public class Main {
        public static void main(String[] args) {

                // Create the shop
                OnlineShop shop = new OnlineShop();

                // Add the initial products

                // BasicT
                BasicT basic1 = new BasicT("1001", "BASIC1", Fabric.COTTON, Color.WHITE, false, false);
                BasicT basic2 = new BasicT("1002", "BASIC2", Fabric.LINEN, Color.BLACK, true, true);
                BasicT basic3 = new BasicT("1003", "BASIC3", Fabric.RAYON, Color.BLACK, true, true);

                // VNeckT
                VNeckT vneck1 = new VNeckT("2001", "VNECK1", Fabric.LYCRA, Color.RED, false);
                VNeckT vneck2 = new VNeckT("2002", "VNECK2", Fabric.LYCRA, Color.RED, true);

                // LongT
                LongT long1 = new LongT("3001", "LONG1", Fabric.POLYESTER, Color.RED, false);
                LongT long2 = new LongT("3002", "LONG2", Fabric.LYCRA, Color.YELLOW, true);

                // TankT
                TankT tank1 = new TankT("4001", "TANK1", Fabric.POLYESTER, Color.RED);
                TankT tank2 = new TankT("4002", "TANK2", Fabric.LYCRA, Color.YELLOW);

                // Cap
                Cap cap1 = new Cap("5001", "CAP1", Color.RED, Visor.FLAT);
                Cap cap2 = new Cap("5002", "CAP2", Color.YELLOW, Visor.CURVED);

                // We add all of them to the stock
                shop.addProduct(basic1);
                shop.addProduct(basic2);
                shop.addProduct(basic3);
                shop.addProduct(vneck1);
                shop.addProduct(vneck2);
                shop.addProduct(long1);
                shop.addProduct(long2);
                shop.addProduct(tank1);
                shop.addProduct(tank2);
                shop.addProduct(cap1);
                shop.addProduct(cap2);

                // --- Imprimimos el stock inicial ---
                System.out.println("=== STOCK INICIAL ===");
                System.out.println(shop.listStock());
                System.out.printf("Valor total: %.2f€%n", shop.stockValue());
                System.out.println("Samarretes: " + shop.numTShirts());
                System.out.println("Gorres: " + shop.numCaps());

                // --- Añadimos un producto extra ---
                BasicT basic4 = new BasicT("1004", "BASIC4", Fabric.COTTON, Color.WHITE, false, false);
                shop.addProduct(basic4);

                System.out.println("\n=== STOCK DESPRES D'AFEGIR BASIC4 ===");
                System.out.println(shop.listStock());
                System.out.printf("Valor total: %.2f€%n", shop.stockValue());
                System.out.println("Samarretes: " + shop.numTShirts());
                System.out.println("Gorres: " + shop.numCaps());

                // --- Vendes amb impressió ---

                // Venem BasicT (basic1) amb print BULL
                boolean sold1 = shop.sellProduct(basic1, PrintImage.BULL);
                System.out.println("\nVenta BasicT (BULL): " + sold1);
                System.out.printf("Nou preu de basic1: %.2f€ | Print: %s%n",
                                basic1.getPrice(), basic1.getPrintImage());

                // Venem VNeckT (vneck1) amb print TIGER
                boolean sold2 = shop.sellProduct(vneck1, PrintImage.TIGER);
                System.out.println("Venta VNeckT (TIGER): " + sold2);
                System.out.printf("Nou preu de vneck1: %.2f€ | Print: %s%n",
                                vneck1.getPrice(), vneck1.getPrintImage());

                // Venem LongT (long1) amb print COBRA
                boolean sold3 = shop.sellProduct(long1, PrintImage.COBRA);
                System.out.println("Venta LongT (COBRA): " + sold3);
                System.out.printf("Nou preu de long1: %.2f€ | Print: %s%n",
                                long1.getPrice(), long1.getPrintImage());

                // Venem TankT (tank1) amb print REINDEER
                boolean sold4 = shop.sellProduct(tank1, PrintImage.REINDEER);
                System.out.println("Venta TankT (REINDEER): " + sold4);
                System.out.printf("Nou preu de tank1: %.2f€ | Print: %s%n",
                                tank1.getPrice(), tank1.getPrintImage());

                // Venem Cap (cap1) amb print EAGLE
                boolean sold5 = shop.sellProduct(cap1, PrintImage.EAGLE);
                System.out.println("Venta Cap (EAGLE): " + sold5);
                System.out.printf("Nou preu de cap1: %.2f€ | Print: %s%n",
                                cap1.getPrice(), cap1.getPrintImage());

                // --- Stock final (sense els productes venuts) ---
                System.out.println("\n=== STOCK FINAL (sense productes venuts) ===");
                System.out.println(shop.listStock());
                System.out.printf("Valor total: %.2f€%n", shop.stockValue());
                System.out.println("Samarretes: " + shop.numTShirts());
                System.out.println("Gorres: " + shop.numCaps());
        }
}