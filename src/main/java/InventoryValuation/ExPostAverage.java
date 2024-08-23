package InventoryValuation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class ExPostAverage implements Solver {

    private double calculatePrice(Collection<Event> events, LongHolder initialCapacity, double initialPrice) {
        if (events == null || events.isEmpty()) {
            return 0;
        }

        double initialAmount = initialCapacity.getValue() * initialPrice;
        long totalCapacity = initialCapacity.getValue();
        for (Event event: events){
            if (event.getType() == EventType.PURCHASE){
                totalCapacity += event.getQuantity();
                initialAmount += (float) (event.getQuantity() * event.getPrice());
            }
        }
        return initialAmount/totalCapacity;
    }

    @Override
    public Queue<Tuple<Long, Double>> processEvents(Collection<Event> events, long initialCapacity, double initialPrice) {
        if(events == null || events.isEmpty()){
            return null;
        }

        float price = Math.round(calculatePrice(events, new LongHolder(initialCapacity), initialPrice) * 100.0) / 100.0f;

        Queue<Tuple<Long, Double>> result = new LinkedList<>();

        for (Event event: events){
            if(event.getType() == EventType.PURCHASE){
                continue;
            }
            if(event.getType() == EventType.CONSUMPTION){
                result.add(new Tuple<>(event.getQuantity(), (double)price));
            }
        }

        return result;
    }
}
