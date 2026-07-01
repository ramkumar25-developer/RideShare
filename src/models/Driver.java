package models;

import java.time.LocalDateTime;

public class Driver {

    private int driverId;
    private String driverName;
    private String email;
    private String password;
    private long mobile;
    private String licenseNumber;
    private String vehicleMake;
    private String vehicleModel;
    private int availableSeats;
    private String registrationStatus;

    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;

    public Driver(int driverId,
                  String driverName,
                  String email,
                  String password,
                  long mobile,
                  String licenseNumber,
                  String vehicleMake,
                  String vehicleModel,
                  int availableSeats,
                  String registrationStatus) {

        this.driverId = driverId;
        this.driverName = driverName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.licenseNumber = licenseNumber;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.availableSeats = availableSeats;
        this.registrationStatus = registrationStatus;

        this.createdOn = LocalDateTime.now();
        this.modifiedOn = LocalDateTime.now();
    }

    public int getDriverId() {
        return driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getMobile() {
        return mobile;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
        this.modifiedOn = LocalDateTime.now();
    }

    @Override
    public String toString() {

        return "Driver ID : " + driverId +
                "\nName : " + driverName +
                "\nEmail : " + email +
                "\nMobile : " + mobile +
                "\nVehicle : " + vehicleMake + " " + vehicleModel +
                "\nSeats : " + availableSeats +
                "\nStatus : " + registrationStatus +
                "\n";
    }
}