package services;

import java.util.Scanner;

import database.Database;
import models.Passenger;
import utils.IdGenerator;
import models.Ride;
import models.Booking;
import models.Rating;


public class PassengerService {

    static Scanner sc = new Scanner(System.in);

    public static void registerPassenger() {

        System.out.println("\n===== PASSENGER REGISTRATION =====");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        // Check duplicate email
        for (Passenger p : Database.passengers) {

            if (p.getEmail().equals(email)) {

                System.out.println("Email already exists!");
                return;
            }
        }

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        System.out.print("Enter Mobile: ");
        long mobile = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter City: ");
        String city = sc.nextLine();

        int passengerId = IdGenerator.generatePassengerId();

        Passenger passenger = new Passenger(
                passengerId,
                name,
                email,
                password,
                mobile,
                city,
                "ACTIVE"
        );

        Database.passengers.add(passenger);

        System.out.println("\nPassenger Registered Successfully!");
        System.out.println("Passenger ID: " + passengerId);
    }
    
    public static void browseRides() {

        boolean found = false;

        System.out.println("\n===== AVAILABLE RIDES =====");

        for (Ride ride : Database.rides) {

            if (ride.getRegistrationStatus().equals("APPROVED")
                    && ride.getRideStatus().equals("ACTIVE")) {

                found = true;

                System.out.println(ride);
            }
        }

        if (!found) {

            System.out.println("No Rides Available");
        }
    }

    public static void bookRide(Passenger passenger) {

        browseRides();

        System.out.print("\nEnter Ride ID to Book: ");
        int rideId = sc.nextInt();

        System.out.print("Enter Number of Seats: ");
        int seats = sc.nextInt();
        sc.nextLine();

        for (Ride ride : Database.rides) {

            if (ride.getRideId() == rideId
                    && ride.getRegistrationStatus().equals("APPROVED")
                    && ride.getRideStatus().equals("ACTIVE")) {

                int bookingId = IdGenerator.generateBookingId();

                Booking booking = new Booking(
                        bookingId,
                        rideId,
                        passenger.getPassengerId(),
                        seats,
                        "BOOKED"
                );

                Database.bookings.add(booking);

                System.out.println("\nRide Booked Successfully!");
                System.out.println("Booking ID: " + bookingId);

                return;
            }
        }

        System.out.println("Invalid Ride ID!");
    }

    public static void viewBookingHistory(Passenger passenger) {

        boolean found = false;

        System.out.println("\n===== BOOKING HISTORY =====");

        for (Booking booking : Database.bookings) {

            if (booking.getPassengerId() == passenger.getPassengerId()) {

                found = true;

                System.out.println(booking);
            }
        }

        if (!found) {

            System.out.println("No Bookings Found!");
        }
    }

    public static void rateDriver(Passenger passenger) {

        viewBookingHistory(passenger);

        System.out.print("\nEnter Ride ID to Rate: ");
        int rideId = sc.nextInt();

        double driverId = -1;

        for (Ride ride : Database.rides) {

            if (ride.getRideId() == rideId) {

                driverId = ride.getDriverId();
                break;
            }
        }

        if (driverId == -1) {

            System.out.println("Invalid Ride ID!");
            return;
        }

        System.out.print("Enter Rating (1 to 5): ");
        double ratingValue = sc.nextDouble();
        sc.nextLine();

        if (ratingValue < 1 || ratingValue > 5) {

            System.out.println("Invalid Rating!");
            return;
        }

        int ratingId = IdGenerator.generateRatingId();

        Rating rating = new Rating(
                ratingId,
                (int) driverId,
                passenger.getPassengerId(),
                ratingValue
        );

        Database.ratings.add(rating);

        System.out.println("Driver Rated Successfully!");
    }

    public static void viewPassengers() {

        if (Database.passengers.isEmpty()) {

            System.out.println("\nNo Passengers Found!");
            return;
        }

        System.out.println("\n===== PASSENGER DETAILS =====");

        for (Passenger passenger : Database.passengers) {

            System.out.println(passenger);
            System.out.println("--------------------------");
        }
    }

    public static void updateProfile(Passenger passenger) {

        System.out.println("\n===== UPDATE PASSENGER PROFILE =====");

        System.out.print("New Name: ");
        String name = sc.nextLine();

        System.out.print("New Mobile Number: ");
        long mobile = sc.nextLong();
        sc.nextLine();

        System.out.print("New City: ");
        String city = sc.nextLine();

        passenger.setPassengerName(name);
        passenger.setMobile(mobile);
        passenger.setCity(city);

        System.out.println("\nProfile Updated Successfully!");
    }

    public static boolean deactivateAccount(Passenger passenger) {

        System.out.print("\nAre you sure? (Y/N): ");

        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("Y")) {

            passenger.setRegistrationStatus("INACTIVE");

            System.out.println("Account Deactivated Successfully!");

            return true;
        }

        System.out.println("Operation Cancelled!");

        return false;
    }
}