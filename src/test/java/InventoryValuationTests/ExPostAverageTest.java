package InventoryValuationTests;

import InventoryValuation.Event;
import InventoryValuation.ExPostAverage;
import InventoryValuation.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class ExPostAverageTest {

    private final static Queue<Event> events = new LinkedList<>();
    private static final Queue<Tuple<Long, Double>> referenceSolution = new LinkedList<>();
    private final ExPostAverage solver = new ExPostAverage();
    private static final long INITIAL_CAPACITY = 9780L;
    private static final float INITIAL_PRICE = 7.1F;

    @BeforeEach
    public void setUp() {
        events.add(new Event(1520, 7.3));
        events.add(new Event(1030));
        events.add(new Event(700));
        events.add(new Event(840, 7.25));
        events.add(new Event(1360, 7.65));
        events.add(new Event(580));
        events.add(new Event(950));

        referenceSolution.add(new Tuple<>(1030L, 7.19));
        referenceSolution.add(new Tuple<>(700L, 7.19));
        referenceSolution.add(new Tuple<>(580L, 7.19));
        referenceSolution.add(new Tuple<>(950L, 7.19));
    }

    @Test
    public void testExPostAverage(){
        Queue<Tuple<Long, Double>> actual = solver.processEvents(events,INITIAL_CAPACITY, INITIAL_PRICE);
        assertEquals(referenceSolution.size(), actual.size());

        for(int i = 0; i < referenceSolution.size(); i++){
            Tuple<Long, Double> ref = referenceSolution.poll();
            Tuple<Long, Double> act = actual.poll();

            assertNotNull(act);

            assertEquals(ref.f, act.f, 0.001);
            assertEquals(ref.s, act.s, 0.001);
        }
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
