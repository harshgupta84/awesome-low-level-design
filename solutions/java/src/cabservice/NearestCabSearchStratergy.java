public class NearestCabSearchStartergy implements CabSearchStartergy{
    private final Double RadiusInKM=5.0;
    public Cab getCab(Location pickup,CarType carType, List<Driver>drivers){

        Double mini=1e16;
        Cab minCab;
        for(Driver it: drivers){
            if(it.cab.CarType!=carType){
                continue;
            }

            Double ditanceFromPickup=pickup.getDistance(it.cab.location)
            if(ditanceFromPickup>RadiusInKM){
                continue;
            }

            if(mini<ditanceFromPickup){
                mini=ditanceFromPickup;
                minCab=it.cab;
            }
        }
        if(mini==1e16){
            return NULL;
        }
        return minCab;
    }
}