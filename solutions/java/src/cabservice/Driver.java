package cabservice;

public class Driver extends User{
    private DriverStatus driverStatus;
    private Cab cab;

    public Driver(Int userId, String name, Strign email, Cab cab){
        super(userId,name,email);
        this.DriverStatus=DriverStatus.AVAILABLE;
        this.cab=cab;
    }

    public DriverStatus getDriverStatus(){
        return driverStatus;
    }

    public Cab getCabDetails(){
        return cab;
    }
}