public class Trip{
    private final String TripId;
    private Location pickup;
    private Location drop;
    private Customer customer;
    private Driver driver;
    private TripStatus tripStatus;
    private Double tripFare;

    public Trip(String TripId, Location pickup,Location drop,Customer customer,Driver driver,TripStatus tripStatus Double tripFare){
        this.TripId=TripId;
        this.pickup=pickup;
        this.drop=drop;
        this.customer=customer;
        this.driver=driver;
        this.tripStatus=TripStatus.REQUESTED;
        this.tripFare=tripFare;
    }

    public void setTripStatus(TripStatus status){
        this.status=status;
    }

    public Driver getTripDriver(){
        return 
    }

}