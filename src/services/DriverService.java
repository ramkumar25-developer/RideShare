package services;

import java.util.Scanner;

import database.Database;
import models.Driver;
import models.Ride;
import utils.IdGenerator;
import java.time.LocalDateTime;
import models.Rating;
import services.AuditService;

public class DriverService {

    static Scanner sc = new Scanner(System.in);

    public static void registerDriver() {

        System.out.println("\n===== DRIVER REGISTRATION =====");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        // Duplicate email check
        for (Driver d : Database.drivers) {

            if (d.getEmail().equals(email)) {

                System.out.println("Email already exists!");
                return;
            }
        }

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        System.out.print("Enter Mobile Number: ");
        long mobile = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter License Number: ");
        String license = sc.nextLine();

        System.out.print("Enter Vehicle Make: ");
        String make = sc.nextLine();

        System.out.print("Enter Vehicle Model: ");
        String model = sc.nextLine();

        System.out.print("Enter Available Seats: ");
        int seats = sc.nextInt();
        sc.nextLine();

        int driverId = IdGenerator.generateDriverId();

        Driver driver = new Driver(
                driverId,
                name,
                email,
                password,
                mobile,
                license,
                make,
                model,
                seats,
                "ACTIVE"
        );

        Database.drivers.add(driver);

        AuditService.createLog("REGISTER","DRIVER","Driver Registered: " + driver.getDriverName());

        System.out.println("\nDriver Registered Successfully!");
        System.out.println("Driver ID: " + driverId);
    }

    public static void createRide(Driver driver) {

        System.out.println("\n===== CREATE RIDE =====");

        System.out.print("Source: ");
        String source = sc.nextLine();

        System.out.print("Destination: ");
        String destination = sc.nextLine();

        System.out.print("Distance (KM): ");
        int distance = sc.nextInt();

        System.out.print("Fare: ");
        double fare = sc.nextDouble();

        sc.nextLine();

        int rideId = utils.IdGenerator.generateRideId();

        Ride ride = new Ride(
                rideId,
                driver.getDriverId(),
                source,
                destination,
                LocalDateTime.now(),
                distance,
                fare,
                "ACTIVE",
                "PENDING"
        );

        Database.rides.add(ride);

        AuditService.createLog("CREATE","RIDE","Ride Created: " + rideId);

        System.out.println("\nRide Created Successfully!");
        System.out.println("Ride ID: " + rideId);
        System.out.println("Waiting for Admin Approval.");
    }

    public static void approveRide() {

        boolean found = false;

        System.out.println("\n===== PENDING RIDES =====");

        for (Ride ride : Database.rides) {

            if (ride.getRegistrationStatus().equals("PENDING")) {

                found = true;

                System.out.println(ride);
            }
        }

        if (!found) {

            System.out.println("No Pending Rides Found");
            return;
        }

        System.out.print("\nEnter Ride ID to Approve: ");

        int rideId = sc.nextInt();
        sc.nextLine();

        for (Ride ride : Database.rides) {

            if (ride.getRideId() == rideId) {

                ride.setRegistrationStatus("APPROVED");

                AuditService.createLog("APPROVE","RIDE","Ride Approved: " + rideId);

                System.out.println("Ride Approved Successfully!");

                return;
            }
        }

        System.out.println("Ride Not Found");
    }
    
    public static void viewDrivers() {

        if (Database.drivers.isEmpty()) {

            System.out.println("No Drivers Found!");
            return;
        }

        System.out.println("\n===== DRIVER DETAILS =====");

        for (Driver driver : Database.drivers) {

            double totalRating = 0;
            int count = 0;

            for (Rating rating : Database.ratings) {

                if (rating.getDriverId() == driver.getDriverId()) {

                    totalRating += rating.getRatingValue();
                    count++;
                }
            }

            double avgRating = 0;

            if (count != 0) {

                avgRating = totalRating / count;
            }

            System.out.println(driver);
            System.out.println("Average Rating : " + avgRating);
            System.out.println("---------------------------");
        }
    }

    public static void terminateDriver() {

        System.out.println("\n===== TERMINATE DRIVER =====");

        viewDrivers();

        System.out.print("\nEnter Driver ID to Terminate: ");
        int driverId = sc.nextInt();
        sc.nextLine();

        for (Driver driver : Database.drivers) {

            if (driver.getDriverId() == driverId) {

                double totalRating = 0;
                int count = 0;

                for (Rating rating : Database.ratings) {

                    if (rating.getDriverId() == driverId) {

                        totalRating += rating.getRatingValue();
                        count++;
                    }
                }

                double avgRating = 0;

                if (count != 0) {

                    avgRating = totalRating / count;
                }

                if (avgRating >= 3.0) {

                    System.out.println(
                        "Cannot terminate! Average rating is above 3.0"
                    );

                    return;
                }

                driver.setRegistrationStatus("INACTIVE");

                System.out.println(
                    "Driver terminated successfully!"
                );

                return;
            }
        }

        System.out.println("Driver not found!");
    }

    public static void viewMyRides(Driver driver) {

        boolean found = false;

        System.out.println("\n===== MY RIDES =====");

        for (Ride ride : Database.rides) {

            if (ride.getDriverId() == driver.getDriverId()) {

                found = true;
                System.out.println(ride);
            }
        }

        if (!found) {

            System.out.println("No Rides Found!");
        }
    }

    public static void editRide(Driver driver) {

        viewMyRides(driver);

        System.out.print("\nEnter Ride ID to Edit: ");
        int rideId = sc.nextInt();
        sc.nextLine();

        for (Ride ride : Database.rides) {

            if (ride.getRideId() == rideId &&
                ride.getDriverId() == driver.getDriverId()) {

                System.out.print("New Source: ");
                String source = sc.nextLine();

                System.out.print("New Destination: ");
                String destination = sc.nextLine();

                System.out.print("New Distance: ");
                int distance = sc.nextInt();

                System.out.print("New Fare: ");
                double fare = sc.nextDouble();
                sc.nextLine();

                ride.setSource(source);
                ride.setDestination(destination);
                ride.setDistance(distance);
                ride.setFare(fare);

                System.out.println("\nRide Updated Successfully!");

                return;
            }
        }

        System.out.println("Invalid Ride ID!");
    }

    public static void cancelRide(Driver driver) {

        viewMyRides(driver);

        System.out.print("\nEnter Ride ID to Cancel: ");
        int rideId = sc.nextInt();
        sc.nextLine();

        for (Ride ride : Database.rides) {

            if (ride.getRideId() == rideId &&
                ride.getDriverId() == driver.getDriverId()) {

                if (ride.getRideStatus().equals("CANCELLED")) {

                    System.out.println("Ride already cancelled!");
                    return;
                }

                ride.setRideStatus("CANCELLED");

                System.out.println("\nRide Cancelled Successfully!");

                return;
            }
        }

        System.out.println("Invalid Ride ID!");
    }

    public static void updateProfile(Driver driver) {

        System.out.println("\n===== UPDATE PROFILE =====");

        System.out.print("New Name: ");
        String name = sc.nextLine();

        System.out.print("New Mobile: ");
        long mobile = sc.nextLong();
        sc.nextLine();

        System.out.print("New Vehicle Make: ");
        String make = sc.nextLine();

        System.out.print("New Vehicle Model: ");
        String model = sc.nextLine();

        System.out.print("New Available Seats: ");
        int seats = sc.nextInt();
        sc.nextLine();

        driver.setDriverName(name);
        driver.setMobile(mobile);
        driver.setVehicleMake(make);
        driver.setVehicleModel(model);
        driver.setAvailableSeats(seats);

        System.out.println("\nProfile Updated Successfully!");
    }
}