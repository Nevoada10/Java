package poker;

/**
 * Main.java
 * Test class for Poker game
 * Tests all classes and their functionality
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Main {
    public static void main(String[] args) {
        
        //============================
        // CARD TESTS
        //============================
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      CARD TESTS                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        // Create 5 cards
        System.out.println("► Creating 5 cards...\n");
        Card card1 = new Card(Suit.HEARTS, Value.ACE);
        Card card2 = new Card(Suit.DIAMONDS, Value.KING);
        Card card3 = new Card(Suit.CLUBS, Value.QUEEN);
        Card card4 = new Card(Suit.SPADES, Value.JACK);
        Card card5 = new Card(Suit.HEARTS, Value.TEN);
        
        // Show cards
        System.out.println("► Showing all cards:");
        System.out.println("  Card 1: " + card1);
        System.out.println("  Card 2: " + card2);
        System.out.println("  Card 3: " + card3);
        System.out.println("  Card 4: " + card4);
        System.out.println("  Card 5: " + card5);
        
        // Show number and code of a card
        System.out.println("\n► Showing number and code of card1:");
        System.out.println("  Number: " + card1.getValue().getNumber());
        System.out.println("  Code: " + card1.getValue().getCode());
        
        //============================
        // HAND TESTS
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      HAND TESTS                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        // Create a hand
        System.out.println("► Creating a poker hand...\n");
        Hand hand = new Hand();
        
        // Create array of 5 cards and add them to hand
        Card[] cards = {card1, card2, card3, card4, card5};
        
        System.out.println("► Adding 5 cards to the hand...");
        for (int i = 0; i < cards.length; i++) {
            hand.addCard(i, cards[i]);
        }
        
        // Show hand
        System.out.println("\n► Showing poker hand:");
        System.out.println("  " + hand);
        
        //============================
        // PLAYER TESTS
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                     PLAYER TESTS                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        // Create 3 players
        System.out.println("► Creating 3 players...\n");
        Player player1 = new Player("Peter", "Brown");
        Player player2 = new Player("Laura", "Wilson");
        Player player3 = new Player("Alex", "Garcia");
        
        // Show players
        System.out.println("► Showing all players:");
        System.out.println("  Player 1: " + player1);
        System.out.println("  Player 2: " + player2);
        System.out.println("  Player 3: " + player3);
        
        //============================
        // POKER TESTS
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                     POKER TESTS                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        // Reset player counter for consistent codes
        Player.resetPlayerCounter();
        
        // Create array of 3 players
        Player[] players = {
            new Player("Peter", "Brown"),
            new Player("Laura", "Wilson"),
            new Player("Alex", "Garcia")
        };
        
        // Create poker game
        System.out.println("► Creating poker game with 3 players...\n");
        Poker poker = new Poker(players);
        
        // Show poker data before initialization
        System.out.println("► Poker data BEFORE initialization:");
        System.out.println(poker);
        
        // Initialize poker
        System.out.println("\n► Initializing poker game...");
        poker.initialize();
        
        // Show poker data after initialization
        System.out.println("\n► Poker data AFTER initialization:");
        System.out.println(poker);
        
        // Give hands to all players
        System.out.println("\n► Dealing hands to all players...\n");
        for (int i = 0; i < players.length; i++) {
            poker.giveHand(players[i]);
            System.out.println("  Hand dealt to " + players[i].getCode());
        }
        
        // Show all players' cards
        System.out.println("\n► Showing all players' cards:");
        for (int i = 0; i < players.length; i++) {
            System.out.println("  " + players[i].getCode() + ": " + players[i].showCards());
        }
        
        // Calculate and show rank for each player
        System.out.println("\n► Calculating rank for each player:");
        for (int i = 0; i < players.length; i++) {
            int rank = poker.getRank(players[i]);
            System.out.println("  " + players[i].getCode() + " rank: " + rank);
        }
        
        // Show players ordered by points
        System.out.println("\n► Players ordered by points (descending):");
        Player[] orderedPlayers = poker.orderClassification();
        for (int i = 0; i < orderedPlayers.length; i++) {
            System.out.println("  " + orderedPlayers[i]);
        }
        
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   ALL TESTS COMPLETED!                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}