public class Event {
    private EventType type;
    private long quantity;
    private double price;

    public Event(long quantity, double price){
        this.quantity = quantity;
        this.price = price;
        this.type = EventType.PURCHASE;
    }

    public Event(long quantity){
        this.quantity = quantity;
        this.price = 0;
        this.type = EventType.CONSUMPTION;
    }

    public EventType getType(){
        return type;
    }

    public long getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
