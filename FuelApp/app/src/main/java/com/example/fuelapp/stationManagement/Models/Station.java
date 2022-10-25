package com.example.fuelapp.stationManagement.Models;

public class Station{

    String Id;
    String stationName;
    String stationNo;
    String stationOwnerName;
    String stationOwnerEmail;
    String password;
    String confirmPassword;


    public Station() {
    }

    public Station(String id, String stationName, String stationNo, String stationOwnerName, String stationOwnerEmail, String password, String confirmPassword) {
        Id = id;
        this.stationName = stationName;
        this.stationNo = stationNo;
        this.stationOwnerName = stationOwnerName;
        this.stationOwnerEmail = stationOwnerEmail;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public String getStationOwnerName() {
        return stationOwnerName;
    }

    public void setStationOwnerName(String stationOwnerName) {
        this.stationOwnerName = stationOwnerName;
    }

    public String getStationOwnerEmail() {
        return stationOwnerEmail;
    }

    public void setStationOwnerEmail(String stationOwnerEmail) {
        this.stationOwnerEmail = stationOwnerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

