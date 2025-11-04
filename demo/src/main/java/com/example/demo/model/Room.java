package com.example.demo.model;

import java.util.List;

public class Room {
    private String id;
    private String hospitalId;
    private int capacity;
    private String number;
    private String status;
    private List<Appointment> appointments;

    public Room() {}

    public Room(String id, String hospitalId, int capacity, String number, String status, List<Appointment> appointments) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.capacity = capacity;
        this.number = number;
        this.status = status;
        this.appointments = appointments;
    }

    public String getId() {
        return id;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", capacity=" + capacity +
                ", number='" + number + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
