import java.util.Collection;

public class ExPostAverage implements Solver{

    @Override
    public float calculatePrice(Collection<Event> events, long initialCapacity, float initialPrice) {
        float initialAmount = initialCapacity * initialPrice;
        long totalCapacity = initialCapacity;
        for (Event event: events){
            if (event.getType() == EventType.PURCHASE){
                totalCapacity += event.getQuantity();
                initialAmount += (float) ((float)event.getQuantity() * event.getPrice());
            }
        }
        return initialAmount/totalCapacity;
    }
}
