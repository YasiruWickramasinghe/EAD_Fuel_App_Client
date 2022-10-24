package com.example.fuelapp.stationManager.Models;

public class StationStatus {
    String id;
    String stationName;
    String stationNumber;
    String stationOwnerName;
    String startTime;
    String endTime;
    String date;
    String fuelType;
    String availability;

    public StationStatus() {
    }

    public StationStatus(String id, String stationName, String stationNumber, String stationOwnerName, String startTime, String endTime, String date, String fuelType, String availability) {
        this.id = id;
        this.stationName = stationName;
        this.stationNumber = stationNumber;
        this.stationOwnerName = stationOwnerName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.fuelType = fuelType;
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getStationOwnerName() {
        return stationOwnerName;
    }

    public void setStationOwnerName(String stationOwnerName) {
        this.stationOwnerName = stationOwnerName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
