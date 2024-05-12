import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class ExPostAverageTest {

    private final static Queue<Event> events = new LinkedList<>();
    private final ExPostAverage solver = new ExPostAverage();
    private static final long INITIAL_CAPACITY = 9780L;
    private static final float INITIAL_PRICE = 7.1F;

    @BeforeAll
    public static void setUp() {
        events.add(new Event(1520, 7.3));
        events.add(new Event(1030));
        events.add(new Event(700));
        events.add(new Event(840, 7.25));
        events.add(new Event(1360, 7.65));
        events.add(new Event(580));
        events.add(new Event(950));
    }

    @Test
    public void testExPostAverage(){
        double price = Math.round(solver.calculatePrice(events, new LongHolder(INITIAL_CAPACITY), INITIAL_PRICE) * 100.0) / 100.0;
        assertEquals(7.19, price, 0.001);
    }

    @Test
    public void testWhenNull(){
        assertEquals(0, solver.calculatePrice(null, new LongHolder(INITIAL_CAPACITY), INITIAL_PRICE));
    }

    @Test
    public void testWhenEmpty(){
        Queue<Event> empty = new LinkedList<>();
        assertEquals(0, solver.calculatePrice(empty, new LongHolder(INITIAL_CAPACITY), INITIAL_PRICE));
    }
}
