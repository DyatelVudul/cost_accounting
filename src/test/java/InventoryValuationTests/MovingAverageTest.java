package InventoryValuationTests;

import InventoryValuation.Event;
import InventoryValuation.MovingAverage;
import InventoryValuation.Tuple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class MovingAverageTest {

    private final static Queue<Event> events = new LinkedList<>();
    private final MovingAverage solver = new MovingAverage();
    private static final Queue<Tuple<Long, Double>> referenceSolution = new LinkedList<>();
    private static final long INITIAL_CAPACITY = 9780L;
    private static final double INITIAL_PRICE = 7.1;

    @BeforeAll
    public static void setUp() {
        events.add(new Event(1520, 7.3));
        events.add(new Event(1030));
        events.add(new Event(700));
        events.add(new Event(840, 7.25));
        events.add(new Event(1360, 7.65));
        events.add(new Event(580));
        events.add(new Event(950));

        referenceSolution.add(new Tuple<>(1030L, 7.13));
        referenceSolution.add(new Tuple<>(700L, 7.13));
        referenceSolution.add(new Tuple<>(580L, 7.20));
        referenceSolution.add(new Tuple<>(950L, 7.20));
    }

    @Test
    public void testMovingPostAverage(){
        Queue<Tuple<Long, Double>> actual = solver.processEvents(events, INITIAL_CAPACITY, INITIAL_PRICE);

        assertEquals(referenceSolution.size(), actual.size());

        for (int i = 0; i < referenceSolution.size(); i++) {
            Tuple<Long, Double> ref = referenceSolution.poll();
            Tuple<Long, Double> act = actual.poll();

            assertNotNull(act);

            assertEquals(ref.f, act.f);
            assertEquals(ref.s, act.s);
        }
    }

    @Test
    public void testWhenNull(){
        assertNull(solver.processEvents(null, 0L, 0F));
    }

    @Test
    public void testWhenEmpty(){
        Queue<Event> empty = new LinkedList<>();
        assertNull(solver.processEvents(empty, 0L, 0F));
    }

}
