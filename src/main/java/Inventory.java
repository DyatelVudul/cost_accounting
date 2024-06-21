import java.util.Queue;

public class Inventory {
    private Queue<Event> events;
    private long stockAmount;
    private double stockPrice;

    private final Solver exPostAverage = new ExPostAverage();
    private final Solver movingAverage = new MovingAverage();
    private final Solver FIFO = new FIFO();
    private final Solver LIFO = new LIFO();

    public Inventory(Queue<Event> events, long stockAmount, double stockPrice){
        this.events = events;
        this.stockAmount = stockAmount;
        this.stockPrice = stockPrice;
    }

    public Queue<Tuple<Long, Double>> processFIFO(){
        return FIFO.processEvents(events, stockAmount, stockPrice);
    }

    public Queue<Tuple<Long, Double>> processLIFO(){
        return LIFO.processEvents(events, stockAmount, stockPrice);
    }

    public Queue<Tuple<Long, Double>> processExPostAverage(){
        return exPostAverage.processEvents(events, stockAmount, stockPrice);
    }

    public Queue<Tuple<Long, Double>> processMovingAverage(){
        return movingAverage.processEvents(events, stockAmount, stockPrice);
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public long getStockAmount() {
        return stockAmount;
    }

    public Queue<Event> getEvents() {
        return events;
    }

    public void setEvents(Queue<Event> events) {
        this.events = events;
    }

    public void setStockAmount(long stockAmount) {
        this.stockAmount = stockAmount;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }
}
