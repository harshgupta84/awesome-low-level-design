public class CabService{
    private static final CabService INSTANCE=new CabService();
    private final Map<String,Driver> drivers;
    private final Map<String,Customers> customers;
    private final Map<String,Cab> cabs;
    private final  Map<String,Trip> trips;

    private final PricingStrategy pricingStrategy;
    private final CabSearchStratergy cabSearchStratergy;

    private CabService(){
        this.drivers=new ConcurrentHashMap<>();
        this.customers=new ConcurrentHashMap<>();
        this.cabs=new ConcurrentHashMap<>();
        this.trips=new ConcurrentHashMap<>();

        this.pricingStrategy=new FixedPricingStatergy();
        this.cabSearchStratergy=new NearestCabSearchStartergy();
    } 

    public Customer registerCustomer(String name, String email){
        String userID=UUID.randomUUID().toString()

        Customer customer=new Customer(
            userID,
            name,
            email
        );

        customers.put(userID,customer);
        return customer;
    }

    public Driver registerDriver(String name, String email,Cab cab){
        String userID=UUID.randomUUID().toString()

        Customer driver=new Driver(
            userID,
            name,
            email,
            cab
        );

        drivers.put(userID,driver);
        return driver;
    }

    public Cab registerCab(Location location, String registrationNumber,CarType carType, String model){
        String cabID=UUID.randomUUID().toString()

        Customer cab=new Cab(
            cabID,
            location,
            registrationNumber,
            carType,
            model
        );

        cabs.put(cabID,cab);
        return cab;
    }

    public synchronized Trip requestRide(Location pickup,Location drop, String userID,CarType carType){
        Customer customer=customers.get(userID);

        if(customer==NULL){
            throw new IllegalArgumentException("Rider not found");
        }

         //Calculate Fare
        Double fare=pricingStrategy.calculateFare(pickup,drop,carType)

        // Seach for a driver
        Driver tempDriver=cabSearchStratergy.getCab(pickup,carType,drivers);

        String tripID=UUID.randomUUID().toString();
        // create a trip
        Trip trip=new Trip(
            tripID,
            pickup,
            drop,
            customer,
            tempDriver
            fare 
        )

        trips.put(tripID,trip)
    }

    public synchronized void startTrip(String tripId){
        Trip trip=trips.get(tripId);

        if(trip==NULL){
            throw new IllegalArgumentException("Trip not found")
        }

        trip.setTripStatus(TripStatus.STARTED)
    }

    public synchronized void cancelTrip(String tripId){
        Trip trip=trips.get(tripId);

        if(trip==NULL){
            throw new IllegalArgumentException("Trip not found")
        }

        trip.setTripStatus(TripStatus.CANCELLED)
    }

    
    public synchronized void completeTrip(String tripId){
        Trip trip=trips.get(tripId);

        if(trip==NULL){
            throw new IllegalArgumentException("Trip not found")
        }

        trip.setTripStatus(TripStatus.CANCELLED)
    }
}
