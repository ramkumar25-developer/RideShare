package database;

import java.util.ArrayList;

import models.Driver;
import models.Passenger;
import models.Ride;
import models.Booking;
import models.Rating;
import models.AuditLog;

public class Database {

    public static ArrayList<Driver> drivers = new ArrayList<>();

    public static ArrayList<Passenger> passengers = new ArrayList<>();

    public static ArrayList<Ride> rides = new ArrayList<>();

    public static ArrayList<Booking> bookings = new ArrayList<>();

    public static ArrayList<Rating> ratings = new ArrayList<>();

    public static ArrayList<AuditLog> auditLogs = new ArrayList<>();

    public static void addAuditLog(AuditLog log) {
        auditLogs.add(log);
    }

}