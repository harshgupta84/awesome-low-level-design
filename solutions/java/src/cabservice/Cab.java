package cabservice;

public class Cab{
    private Int cabId;
    private Location location;
    private String registrationNumber;
    private CarType carType;
    private String model;

    public Cab(Int cabId,Location location,String registrationNumber,CarType carType,String model){
        this.cabId=cabId;
        this.location=location;
        this.registrationNumber=registrationNumber;
        this.carType=carType;
        this.model=model;
    }

    public Location getCarLocation(){
        return location;
    }

    public String getCarModel(){
        return model;
    }

    public CarType getCarType(){
        return carType;
    }

}