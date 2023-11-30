package assignment1;

public class Flight {

    private String destination;
    private int price;
    private String fromDate;
    private String toDate;

    public Flight(String destination, int price, String fromDate, String toDate) {
        this.destination = destination;
        this.price = price;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    public String getDestination() {
        return destination;
    }

    public int getPrice() {
        return price;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "destination='" + destination + '\'' +
                ", price=" + price +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
