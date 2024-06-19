import java.util.Collection;
import java.util.Queue;

public interface QueueSolver {
    Queue<Tuple<Long, Double>> processEvents(Collection<Event> events, long initialCapacity, double initialPrice);
}
