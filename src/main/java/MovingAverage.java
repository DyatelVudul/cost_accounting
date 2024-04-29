import java.util.Collection;

public class MovingAverage implements Solver{
    @Override
    public float calculatePrice(Collection<Event> events, long initialCapacity, float initialPrice) {
        float totalAmount = initialCapacity * initialPrice;
        long totalCapacity = initialCapacity;
        for (Event event: events){
            if (event.getType() == EventType.CONSUMPTION){
                break;
            }
            if (event.getType() == EventType.PURCHASE){
                totalCapacity += event.getQuantity();
                totalAmount += (float) ((float)event.getQuantity() * event.getPrice());
            }
        }
        return totalAmount/totalCapacity;
    }
}
