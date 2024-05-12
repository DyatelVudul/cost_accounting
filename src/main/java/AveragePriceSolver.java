import java.util.Collection;

public interface AveragePriceSolver {
    float calculatePrice(Collection<Event> events, Long initialCapacity, float initialPrice);
}
