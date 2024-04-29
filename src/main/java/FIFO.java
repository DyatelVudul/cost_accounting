import java.util.*;

public class FIFO implements Solver{
    @Override
    public float calculatePrice(Collection<Event> events, long initialCapacity, float initialPrice) {
        // TODO:
        LinkedList<Event> purchases = new LinkedList<>();

        for (Event event: events){
            if(event.getType() == EventType.PURCHASE){ // Process purchase
                purchases.addLast(event);
                System.out.println("Purchased " + event.getQuantity() + "kg for a price " + event.getPrice() + " Euro.");
            } else { // Process consumption
                while (event.getQuantity() > 0){
                    Event tmp = purchases.getFirst();

                }
            }
        }
        return 0;
    }
}
