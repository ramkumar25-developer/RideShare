package models;

import java.time.LocalDateTime;

public class Ride {

    private int rideId;
    private int driverId;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private int distance;
    private double fare;

    private String rideStatus;
    private String registrationStatus;

    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;

    public Ride(int rideId,
                int driverId,
                String source,
                String destination,
                LocalDateTime departureTime,
                int distance,
                double fare,
                String rideStatus,
                String registrationStatus) {

        this.rideId = rideId;
        this.driverId = driverId;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.distance = distance;
        this.fare = fare;
        this.rideStatus = rideStatus;
        this.registrationStatus = registrationStatus;

        this.createdOn = LocalDateTime.now();
        this.modifiedOn = LocalDateTime.now();
    }

    public int getRideId() {
        return rideId;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public int getDistance() {
        return distance;
    }

    public double getFare() {
        return fare;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String status) {
        this.registrationStatus = status;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setFare(double fare) {
        this.fare = fare;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setSource(String source) {
        this.source = source;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setDestination(String destination) {
        this.destination = destination;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setDistance(int distance) {
        this.distance = distance;
        this.modifiedOn = LocalDateTime.now();
    }

    @Override
    public String toString() {

        return "Ride ID : " + rideId +
                "\nDriver ID : " + driverId +
                "\nSource : " + source +
                "\nDestination : " + destination +
                "\nDistance : " + distance +
                "\nFare : " + fare +
                "\nRide Status : " + rideStatus +
                "\nApproval Status : " + registrationStatus +
                "\n";
    }
}
