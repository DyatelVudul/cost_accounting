import java.util.Collection;

public interface AveragePriceSolver {
    float calculatePrice(Collection<Event> events, LongHolder initialCapacity, float initialPrice);
}
