import java.util.Collection;

public class ExPostAverage implements AveragePriceSolver {

    @Override
    public float calculatePrice(Collection<Event> events, LongHolder initialCapacity, float initialPrice) {
        if (events == null || events.isEmpty()) {
            return 0;
        }

        float initialAmount = initialCapacity.getValue() * initialPrice;
        long totalCapacity = initialCapacity.getValue();
        for (Event event: events){
            if (event.getType() == EventType.PURCHASE){
                totalCapacity += event.getQuantity();
                initialAmount += (float) (event.getQuantity() * event.getPrice());
            }
        }
        return initialAmount/totalCapacity;
    }
}
