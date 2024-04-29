import java.util.Collection;

public interface Solver {
    float calculatePrice(Collection<Event> events, long initialCapacity, float initialPrice);
}
