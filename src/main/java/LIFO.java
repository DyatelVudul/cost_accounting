import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LIFO implements QueueSolver{

    @Override
    public Queue<Tuple<Long, Double>> processEvents(Collection<Event> events, long initialCapacity, double initialPrice) {
        if  (events == null || events.isEmpty()) {
            return null;
        }

        Queue<Tuple<Long, Double>> result = new LinkedList<>();
        long inCapacity = initialCapacity;

        Stack<Event> purchases = new Stack<>();

        for (Event event : events) {
            if (event.getType() == EventType.PURCHASE){
                purchases.push(event);
                continue;
            }

            if (event.getType() == EventType.CONSUMPTION){
                while (true){
                    if (purchases.isEmpty() || event.getQuantity() == 0){ // If Consumption need 0 units or no more Purchases are available
                        break;
                    }

                    Event purchase = purchases.peek();

                    result.add(new Tuple<>(purchase.getQuantity(), purchase.getPrice()));

                    if (purchase.getQuantity() >= event.getQuantity()){ // If Purchase can satisfy Consumption
                        purchase.setQuantity(purchase.getQuantity() - event.getQuantity());
                        event.setQuantity(0);
                        break;
                    }else { // If Purchase cannot satisfy Consumption
                        event.setQuantity(event.getQuantity() - purchase.getQuantity());
                        purchases.pop();
                    }
                }

                if(event.getQuantity() > 0){ // Case if none of the purchases can satisfy Consumption
                    if(event.getQuantity() > inCapacity){
                        throw new RuntimeException("Cannot satisfy consumption: No more units are available");
                    }

                    inCapacity -= event.getQuantity();
                    result.add(new Tuple<>(event.getQuantity(), initialPrice));
                }
            }
        }

        return result;
    }
}
