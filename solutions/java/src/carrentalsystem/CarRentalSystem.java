package carrentalsystem;

import carrentalsystem.payment.CreditCardPaymentProcessor;
import carrentalsystem.payment.PaymentProcessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CarRentalSystem {

    private static final CarRentalSystem INSTANCE = new CarRentalSystem();

    private final Map<String, Car> cars;
    private final Map<String, Reservation> reservations;
    private final PaymentProcessor paymentProcessor;

    private CarRentalSystem() {
        cars = new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
        paymentProcessor = new CreditCardPaymentProcessor();
    }

    public static CarRentalSystem getInstance() {
        return INSTANCE;
    }

    public void addCar(Car car) {
        cars.put(car.getLicensePlate(), car);
    }

    public void removeCar(String licensePlate) {
        cars.remove(licensePlate);
    }

    public List<Car> searchCars(String brand, String model,
                                LocalDate startDate, LocalDate endDate) {

        List<Car> availableCars = new ArrayList<>();

        for (Car car : cars.values()) {
            if (car.getBrand().equalsIgnoreCase(brand)
                    && car.getModel().equalsIgnoreCase(model)
                    && car.getCarStatus() != CarStatus.OUT_OF_SERVICE
                    && isCarAvailable(car, startDate, endDate)) {

                availableCars.add(car);
            }
        }

        return availableCars;
    }

    private boolean isCarAvailable(Car car,
                                   LocalDate startDate,
                                   LocalDate endDate) {

        for (Reservation reservation : reservations.values()) {
            if (reservation.getCar().equals(car)
                    && reservation.getStatus() == ReservationStatus.CONFIRMED) {

                boolean isOverlapping =
                        !endDate.isBefore(reservation.getStartDate())
                                && !startDate.isAfter(reservation.getEndDate());

                if (isOverlapping) {
                    return false;
                }
            }
        }

        return true;
    }

    public synchronized Reservation makeReservation(Customer customer,
                                                    Car car,
                                                    LocalDate startDate,
                                                    LocalDate endDate) {

        if (car.getCarStatus() == CarStatus.OUT_OF_SERVICE) {
            throw new IllegalStateException("Car is out of service");
        }

        if (!isCarAvailable(car, startDate, endDate)) {
            throw new IllegalStateException("Car is not available for selected dates");
        }

        String reservationId = generateReservationId();

        Reservation reservation = new Reservation(
                reservationId,
                customer,
                car,
                startDate,
                endDate
        );

        reservations.put(reservationId, reservation);
        car.setCarStatus(CarStatus.RESERVED);

        return reservation;
    }

    public synchronized void cancelReservation(String reservationId) {
        Reservation reservation = reservations.get(reservationId);

        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found");
        }

        reservation.cancel();
        reservation.getCar().setCarStatus(CarStatus.AVAILABLE);
    }

    public boolean processPayment(Reservation reservation) {
        return paymentProcessor.processPayment(reservation.getTotalPrice());
    }

    private String generateReservationId() {
        return "RES" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }
}