package models;

import java.time.LocalDateTime;

public class Rating {

    private int ratingId;
    private int driverId;
    private int passengerId;

    private double ratingValue;

    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;

    public Rating(int ratingId,
                  int driverId,
                  int passengerId,
                  double ratingValue) {

        this.ratingId = ratingId;
        this.driverId = driverId;
        this.passengerId = passengerId;
        this.ratingValue = ratingValue;

        this.createdOn = LocalDateTime.now();
        this.modifiedOn = LocalDateTime.now();
    }

    public int getDriverId() {
        return driverId;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    @Override
    public String toString() {

        return "Rating ID : " + ratingId +
                "\nDriver ID : " + driverId +
                "\nPassenger ID : " + passengerId +
                "\nRating : " + ratingValue +
                "\n";
    }
}