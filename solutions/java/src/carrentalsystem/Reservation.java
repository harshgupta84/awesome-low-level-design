package carrentalsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

    private final String reservationId;
    private final Customer customer;
    private final Car car;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;
    private ReservationStatus status;

    public Reservation(String reservationId, Customer customer, Car car,
                       LocalDate startDate, LocalDate endDate) {

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        this.reservationId = reservationId;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calculateTotalPrice();
        this.status = ReservationStatus.CONFIRMED;
    }

    private double calculateTotalPrice() {
        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return car.getRentalPricePerDay() * days;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }

    public void complete() {
        this.status = ReservationStatus.COMPLETED;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}