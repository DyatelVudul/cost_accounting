package InventoryValuationTests;

import InventoryValuation.Event;
import InventoryValuation.EventType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void testPurchaseConstructor(){
        Event event = new Event(100, 100.0);
        assertEquals(100, event.getQuantity());
        assertEquals(100.0, event.getPrice(), 1e-12);
        assertEquals(EventType.PURCHASE, event.getType());
    }

    @Test
    public void testConsumptionConstructor(){
        Event event = new Event(100);
        assertEquals(100, event.getQuantity());
        assertEquals(0, event.getPrice(), 1e-12);
        assertEquals(EventType.CONSUMPTION, event.getType());
    }
}
