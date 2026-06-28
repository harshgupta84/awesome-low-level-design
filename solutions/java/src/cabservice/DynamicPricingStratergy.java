public class DynamicPricingStatergy impelements PricingStrategy{
    public Double calculateFare(Location pickup,Location Drop,CarType CarType){
        Double distanceInKm=pickup.getDistance(Drop);
        private final Double BaseRate=15.0;

        if(distanceInKm>10.0){
            BaseRate-=5.0;
        }
        if(carType==CarType.AUTO){
            return distanceInKm*(BaseRate+5.0);
        }else if(carType==CarType.SEDAN){
            return distanceInKm*(BaseRate+15.0);;
        }else if(carType==CarType.SUV){
            return distanceInKm*(BaseRate+25.0);;
        }
    }
}