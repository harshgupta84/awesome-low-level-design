public class FixedPricingStatergy impelements PricingStrategy{
    public Double calculateFare(Location pickup,Location Drop,CarType CarType){
        Double distanceInKm=pickup.getDistance(Drop);

        if(carType==CarType.AUTO){
            return distanceInKm*15.0;
        }else if(carType==CarType.SEDAN){
            return distanceInKm*25.0;
        }else if(carType==CarType.SUV){
            return distanceInKm*35.0;
        }
    }
}