import java.util.Queue;

public class Inventory {
    private Queue<Event> events;
    private long stockAmount;
    private float stockPrice;
    public Inventory(Queue<Event> events, long stockAmount, float stockPrice){
        this.events = events;
        this.stockAmount = stockAmount;
        this.stockPrice = stockPrice;
    }

    public void processEvents(){

    }
}
