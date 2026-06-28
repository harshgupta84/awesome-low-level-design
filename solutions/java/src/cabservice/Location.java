package cabservice;

public class Location{
    private Double latitude;
    private Double longitude;
    private final Double conversionNumber=1.2234;

    public Location(Double latitude, Double longitude){
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Double getDistance(Location target){
        return (abs(target.latitude-this.latitude)+abs(target.longitude-this.longitude))*conversionNumber;
    }
}