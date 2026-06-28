package cabservice;

public class Customer extends User{
    private List<Trip> tripHistory;

    public Customer(Int userId, String name, Strign email,Trip trip){
        super(userId,name,email);
        tripHistory= new ArrayList<>();
    }
}