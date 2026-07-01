package models;

import java.time.LocalDateTime;

public class Passenger {

    private int passengerId;
    private String passengerName;
    private String email;
    private String password;
    private long mobile;
    private String city;
    private String registrationStatus;

    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;

    public Passenger(int passengerId,
                     String passengerName,
                     String email,
                     String password,
                     long mobile,
                     String city,
                     String registrationStatus) {

        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.city = city;
        this.registrationStatus = registrationStatus;

        this.createdOn = LocalDateTime.now();
        this.modifiedOn = LocalDateTime.now();
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getPassengerName() {
        return passengerName;
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

    public String getCity() {
        return city;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setPassengerName(String passengerName) {

        this.passengerName = passengerName;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setMobile(long mobile) {

        this.mobile = mobile;
        this.modifiedOn = LocalDateTime.now();
    }

    public void setCity(String city) {

        this.city = city;
        this.modifiedOn = LocalDateTime.now();
    }

    @Override
    public String toString() {

        return "Passenger ID : " + passengerId +
                "\nName : " + passengerName +
                "\nEmail : " + email +
                "\nMobile : " + mobile +
                "\nCity : " + city +
                "\nStatus : " + registrationStatus +
                "\n";
    }
}