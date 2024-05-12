import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovingAverageTest {

    private final static Queue<Event> events = new LinkedList<>();
    private final MovingAverage solver = new MovingAverage();

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
    public void testMovingPostAverage(){
        LongHolder initialCapacity = new LongHolder(9780);
        float initialPrice = 7.10f;

        initialPrice = (float) (Math.round(solver.calculatePrice(events, initialCapacity, initialPrice) * 100.0) / 100.0);

        // First Iteration
        assertEquals(7.13, initialPrice, 0.001);
        assertEquals(5, events.size());
        assertEquals(10270, initialCapacity.getValue());

        // Second Iteration
        initialPrice = (float) (Math.round(solver.calculatePrice(events, initialCapacity, initialPrice) * 100.0) / 100.0);
        assertEquals(7.13, initialPrice, 0.001);
        assertEquals(4, events.size());
        assertEquals(9570, initialCapacity.getValue());

        // Third Iteration
        initialPrice = (float) (Math.round(solver.calculatePrice(events, initialCapacity, initialPrice) * 100.0) / 100.0);
        assertEquals(7.20, initialPrice, 0.001);
        assertEquals(1, events.size());
        assertEquals(11190, initialCapacity.getValue());

        // Fourth Iteration
        initialPrice = (float) (Math.round(solver.calculatePrice(events, initialCapacity, initialPrice) * 100.0) / 100.0);
        assertEquals(7.20, initialPrice, 0.001);
        assertEquals(0, events.size());
        assertEquals(10240, initialCapacity.getValue());

    }

    @Test
    public void testWhenNull(){
        assertEquals(0, solver.calculatePrice(null, new LongHolder(0L), 0F));
    }

    @Test
    public void testWhenEmpty(){
        Queue<Event> empty = new LinkedList<>();
        assertEquals(0, solver.calculatePrice(empty, new LongHolder(0L), 0F));
    }

}
