package carrentalsystem;

public class Car {

    private final String id;
    private final String brand;
    private final String model;
    private final String licensePlate;
    private final double rentalPricePerDay;
    private final CarType carType;
    private CarStatus carStatus;

    public Car(String id, String brand, String model, String licensePlate,
               double rentalPricePerDay, CarType carType) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.rentalPricePerDay = rentalPricePerDay;
        this.carType = carType;
        this.carStatus = CarStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public CarType getCarType() {
        return carType;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public boolean isAvailable() {
        return carStatus == CarStatus.AVAILABLE;
    }
}