package InventoryValuation;

import java.util.*;


public class FIFO implements Solver {

    @Override
    public Queue<Tuple<Long, Double>> processEvents(Collection<Event> events, long initialCapacity, double initialPrice) throws RuntimeException {
        if (events == null || events.isEmpty()) {
            return null;
        }

        Queue<Tuple<Long, Double>> result = new LinkedList<>();

        Queue<Event> purchases = new LinkedList<>();

        purchases.add(new Event(initialCapacity, initialPrice));

        for (Event event : events) {
            if (event.getType() == EventType.PURCHASE){
                purchases.add(event);
                continue;
            }

            if (event.getType() == EventType.CONSUMPTION){
                while (true){
                    if (purchases.isEmpty() && event.getQuantity() > 0){ // If Consumption need 0 units or no more Purchases are available
                        throw new RuntimeException("Not enough inventory to process all purchase events");
                    }

                    Event purchase = purchases.peek();

                    if(purchase == null){
                        throw new RuntimeException("Purchase event is null");
                    }

                    result.add(new Tuple<>(Math.min(purchase.getQuantity(), event.getQuantity()), purchase.getPrice()));

                    if (purchase.getQuantity() >= event.getQuantity()){ // If Purchase can satisfy Consumption
                        purchase.setQuantity(purchase.getQuantity() - event.getQuantity());
                        event.setQuantity(0);
                        break;
                    } else { // If Purchase cannot satisfy Consumption
                        event.setQuantity(event.getQuantity() - purchase.getQuantity());
                        purchases.remove();
                    }
                }
            }
        }

        return result;
    }
}
