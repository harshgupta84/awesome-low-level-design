package carrentalsystem;

import java.time.LocalDate;
import java.util.List;

public class CarRentalSystemDemo {

    public static void run() {
        CarRentalSystem carRentalSystem = CarRentalSystem.getInstance();

        carRentalSystem.addCar(
                new Car("C1", "Toyota", "Camry", "ABC123", 50.0, CarType.SEDAN)
        );

        carRentalSystem.addCar(
                new Car("C2", "Honda", "Civic", "XYZ789", 45.0, CarType.SEDAN)
        );

        carRentalSystem.addCar(
                new Car("C3", "Ford", "Mustang", "DEF456", 80.0, CarType.SEDAN)
        );

        Customer customer1 = new Customer(
                "CU1",
                "John Doe",
                "john@example.com",
                "DL1234"
        );

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);

        List<Car> availableCars =
                carRentalSystem.searchCars("Toyota", "Camry", startDate, endDate);

        if (availableCars.isEmpty()) {
            System.out.println("No cars available.");
            return;
        }

        Car selectedCar = availableCars.get(0);

        Reservation reservation =
                carRentalSystem.makeReservation(customer1, selectedCar, startDate, endDate);

        boolean paymentSuccess = carRentalSystem.processPayment(reservation);

        if (paymentSuccess) {
            System.out.println("Reservation successful: "
                    + reservation.getReservationId());
        } else {
            carRentalSystem.cancelReservation(reservation.getReservationId());
            System.out.println("Payment failed. Reservation cancelled.");
        }
    }
}