import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class TestInventory {

    public final long STOCK_AMOUNT = 9780;
    public final double STOCK_PRICE = 7.1;
    public Queue<Event> events = new LinkedList<>();

    @BeforeEach
    public void setUp() {
        events.add(new Event(1520, 7.3));
        events.add(new Event(1030));
    }

    @Test
    public void testConstructor() {
        Inventory inventory = new Inventory(events, STOCK_AMOUNT, STOCK_PRICE);

        assertEquals(STOCK_AMOUNT, inventory.getStockAmount());
        assertEquals(STOCK_PRICE, inventory.getStockPrice());

        Queue<Event> storedEvents = inventory.getEvents();

        assertNotNull(storedEvents);

        assertEquals(2, storedEvents.size());

        Event event = storedEvents.poll();

        assertNotNull(event);

        assertEquals(EventType.PURCHASE, event.getType());
        assertEquals(1520, event.getQuantity());
        assertEquals(7.3, event.getPrice(), 1e-12);

        event = storedEvents.poll();

        assertNotNull(event);

        assertEquals(EventType.CONSUMPTION, event.getType());
        assertEquals(1030, event.getQuantity());
    }

}
