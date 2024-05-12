import java.util.Collection;

public interface AveragePriceSolver {
    float calculatePrice(Collection<Event> events, long initialCapacity, float initialPrice);
}
