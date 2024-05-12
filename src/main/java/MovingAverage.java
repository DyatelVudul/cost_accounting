import java.util.Collection;
import java.util.Iterator;

public class MovingAverage implements AveragePriceSolver {
    @Override
    public float calculatePrice(Collection<Event> events, LongHolder initialCapacity, float initialPrice) {
        if(events == null || events.isEmpty()) {
            return 0;
        }

        long initialCapacityValue = initialCapacity.getValue();

        float totalAmount = initialCapacityValue * initialPrice;
        long totalCapacity = initialCapacityValue;
        for (Iterator<Event> iterator = events.iterator(); iterator.hasNext(); ){
            Event event = iterator.next();

            if (event.getType() == EventType.CONSUMPTION){
                initialCapacity.setValue(initialCapacity.getValue() - event.getQuantity());
                iterator.remove();
                break;
            }

            if (event.getType() == EventType.PURCHASE){
                totalCapacity += event.getQuantity();
                initialCapacity.setValue(initialCapacity.getValue() + event.getQuantity());
                totalAmount += (float) (event.getQuantity() * event.getPrice());
            }
            iterator.remove();
        }

        return totalAmount/totalCapacity;
    }
}
