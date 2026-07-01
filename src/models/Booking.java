package models;

import java.time.LocalDateTime;

public class Booking {

    private int bookingId;
    private int rideId;
    private int passengerId;
    private int seatsBooked;

    private String bookingStatus;

    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;

    public Booking(int bookingId,
                   int rideId,
                   int passengerId,
                   int seatsBooked,
                   String bookingStatus) {

        this.bookingId = bookingId;
        this.rideId = rideId;
        this.passengerId = passengerId;
        this.seatsBooked = seatsBooked;
        this.bookingStatus = bookingStatus;

        this.createdOn = LocalDateTime.now();
        this.modifiedOn = LocalDateTime.now();
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getRideId() {
        return rideId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    @Override
    public String toString() {

        return "Booking ID : " + bookingId +
                "\nRide ID : " + rideId +
                "\nPassenger ID : " + passengerId +
                "\nSeats : " + seatsBooked +
                "\nStatus : " + bookingStatus +
                "\n";
    }
}