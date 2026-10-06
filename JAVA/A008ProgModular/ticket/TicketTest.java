package A008ProgModular.ticket;

/**
 * This class contains a test for the Ticket class.
 * It instantiates a Ticket object and calls its generateTicket method with the amounts array.
 * 
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class TicketTest {

    private static final float[][] amounts = {
    {1f, 1f, 3.80f},
    {2f, 1f, 2.00f},
    {3f, 3f, 2.20f},
    {4f, 5f, 2.90f},
    {5f, 10f, 3.20f},
    {6f, 12f, 3.70f},
    {7f, 5f, 3.10f},
    {8f, 7f, 3.60f},
    {9f, 12f, 2.70f}
    };

    /**
     * This is the main method of the class TicketTest.
     * It instantiates a Ticket object and calls its generateTicket method with the amounts array.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ticket.generateTicket(amounts);
    }

} // End Class TicketTest
// END
