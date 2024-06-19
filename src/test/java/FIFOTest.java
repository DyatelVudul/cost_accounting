import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FIFOTest {
    private final static Queue<Event> events = new LinkedList<>();
    private final FIFO solver = new FIFO();
    private final static Queue<Tuple<Long,Double>> referenceSolution = new LinkedList<>();
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

        referenceSolution.add(new Tuple<>(1030L, 7.1));
        referenceSolution.add(new Tuple<>(700L, 7.1));
        referenceSolution.add(new Tuple<>(580L, 7.1));
        referenceSolution.add(new Tuple<>(950L, 7.1));
    }

    @Test
    public void testExPostAverage(){
        Queue<Tuple<Long, Double>> processes = solver.processEvents(events, INITIAL_CAPACITY, INITIAL_PRICE);
        assertEquals(referenceSolution.size(), processes.size());
    }

    @Test
    public void testWhenNull(){
        assertNull(solver.processEvents(null, INITIAL_CAPACITY, INITIAL_PRICE));
    }

    @Test
    public void testWhenEmpty(){
        Queue<Event> empty = new LinkedList<>();
        assertNull(solver.processEvents(empty, INITIAL_CAPACITY, INITIAL_PRICE));
    }
}
