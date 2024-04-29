import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventFactoryTest {
    @Test
    public void testCreatePurchase(){
        Event event = EventFactory.createPurchase(100, 100.0F);
        assertEquals(100, event.getQuantity());
        assertEquals(100.0, event.getPrice(), 1e-12);
        assertEquals(EventType.PURCHASE, event.getType());
    }

    @Test
    public void testCreateConsumption(){
        Event event = EventFactory.createConsumption(100);
        assertEquals(100, event.getQuantity());
        assertEquals(0, event.getPrice(), 1e-12);
        assertEquals(EventType.CONSUMPTION, event.getType());
    }
}
