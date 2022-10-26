package com.example.fuelapp.queueManagement.Models;

public class Queue {

    String Id;
    String vehicleNo;
    String vehicleType;
    String stationId;
    String stationName;
    String fuelType;
    String arrivalTime;
    String departTime;
    String currentDate;
    String pumpStatus;

    public Queue(String id, String vehicleNo, String vehicleType, String stationId, String stationName, String fuelType, String arrivalTime, String departTime, String currentDate, String pumpStatus) {
        Id = id;
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.stationId = stationId;
        this.stationName = stationName;
        this.fuelType = fuelType;
        this.arrivalTime = arrivalTime;
        this.departTime = departTime;
        this.currentDate = currentDate;
        this.pumpStatus = pumpStatus;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getPumpStatus() {
        return pumpStatus;
    }

    public void setPumpStatus(String pumpStatus) {
        this.pumpStatus = pumpStatus;
    }
}
