import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage implements Solver {

    @Override
    public Queue<Tuple<Long, Double>> processEvents(Collection<Event> events, long initialCapacity, double initialPrice) throws RuntimeException {
        if (events == null || events.isEmpty()) {
            return null;
        }

        Queue<Tuple<Long, Double>> result = new LinkedList<>();

        for (Event event : events) {
            if (event.getType() == EventType.PURCHASE){
                initialPrice = Math.round( (event.getPrice()*event.getQuantity() + initialPrice*initialCapacity)
                        /(initialCapacity + event.getQuantity()) * 100.0) / 100.0;
                initialCapacity += event.getQuantity();
                continue;
            }

            if (event.getQuantity() > initialCapacity){
                throw new RuntimeException("Not enough Inventory to satisfy Consumption");
            }

            if (event.getType() == EventType.CONSUMPTION){
                result.add(new Tuple<>(event.getQuantity(), initialPrice));
                initialCapacity -= event.getQuantity();
            }
        }

        return result;
    }
}
