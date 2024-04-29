public class EventFactory {
    public static Event createPurchase(long quantity, float price){
        return new Event(quantity, price);
    }

    public static Event createConsumption(long quantity){
        return new Event(quantity);
    }
}
