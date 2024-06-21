import java.util.Collection;
import java.util.Queue;

public interface Solver {
    Queue<Tuple<Long, Double>> processEvents(Collection<Event> events, long initialCapacity, double initialPrice);
}
