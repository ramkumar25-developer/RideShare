package services;

import java.util.Scanner;

import database.Database;
import models.Driver;
import models.Passenger;
import services.DriverService;
import services.PassengerService;
import services.AuditService;

public class LoginService {

    static Scanner sc = new Scanner(System.in);

    public static void login() {

        System.out.println("\n===== LOGIN =====");

        System.out.println("1. Admin Login");
        System.out.println("2. Driver Login");
        System.out.println("3. Passenger Login");

        System.out.print("Enter Choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                adminLogin();
                break;

            case 2:
                driverLogin();
                break;

            case 3:
                passengerLogin();
                break;

            default:
                System.out.println("Invalid Choice");
        }
    }

    public static void adminLogin() {

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        if (username.equals("admin")
                && password.equals("admin123")) {

            System.out.println("\nAdmin Login Successful");

            adminMenu();
        }
        else {

            System.out.println("Invalid Credentials");
        }
    }

    public static void driverLogin() {

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        for (Driver d : Database.drivers) {

            if (d.getEmail().equals(email)
                    && d.getPassword().equals(password)) {

                System.out.println("\nDriver Login Successful");
                System.out.println("Welcome " + d.getDriverName());

                driverMenu(d);

                return;
            }
        }

        System.out.println("Invalid Credentials");
    }

    public static void passengerLogin() {

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        for (Passenger p : Database.passengers) {

            if (p.getEmail().equals(email) && p.getPassword().equals(password)) {

                if (p.getRegistrationStatus().equals("INACTIVE")) {

                    System.out.println("Account is deactivated!");
                    return;
                }

                System.out.println("\nPassenger Login Successful");
                System.out.println("Welcome " + p.getPassengerName());

                passengerMenu(p);

                return;
            }
        }

        System.out.println("Invalid Credentials");
    }

    public static void adminMenu() {

        while (true) {

            System.out.println("\n===== ADMIN MENU =====");

            System.out.println("1. Approve Rides");
            System.out.println("2. View Drivers");
            System.out.println("3. View Passengers");
            System.out.println("4. Terminate Driver");
            System.out.println("5. View Audit Logs");
            System.out.println("6. Logout");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    DriverService.approveRide();
                    break;

                case 2:
                    DriverService.viewDrivers();
                    break;

                case 3:
                    PassengerService.viewPassengers();
                    break;

                case 4:
                    DriverService.terminateDriver();
                    break;

                case 5:
                    AuditService.viewLogs();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public static void driverMenu(Driver driver) {

        while (true) {

            System.out.println("\n===== DRIVER MENU =====");

            System.out.println("1. Create Ride");
            System.out.println("2. View My Rides");
            System.out.println("3. Edit Ride");
            System.out.println("4. Cancel Ride");
            System.out.println("5. Update Profile");
            System.out.println("6. Logout");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    DriverService.createRide(driver);
                    break;

                case 2:
                    DriverService.viewMyRides(driver);
                    break;

                case 3:
                    DriverService.editRide(driver);
                    break;

                case 4:
                    DriverService.cancelRide(driver);
                    break;

                case 5:
                    DriverService.updateProfile(driver);
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public static void passengerMenu(Passenger passenger) {

        while (true) {

            System.out.println("\n===== PASSENGER MENU =====");

            System.out.println("1. Browse Rides");
            System.out.println("2. Book Ride");
            System.out.println("3. Booking History");
            System.out.println("4. Rate Driver");
            System.out.println("5. Update Profile");
            System.out.println("6. Deactivate Account");
            System.out.println("7. Logout"); 

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    PassengerService.browseRides();
                    break;

                case 2:
                    PassengerService.bookRide(passenger);
                    break;

                case 3:
                    PassengerService.viewBookingHistory(passenger);
                    break;

                case 4:
                    PassengerService.rateDriver(passenger);
                    break;

                case 5:
                    PassengerService.updateProfile(passenger);
                    break;

                case 6:

                    boolean deactivated =
                            PassengerService.deactivateAccount(passenger);

                    if (deactivated) {
                        return;
                    }

                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}