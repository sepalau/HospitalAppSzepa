package com.example.demo.model;

import java.util.List;

public class Patient {

    private String id;
    private String name;
    private List<Appointment> appointments;

    public Patient() {
    }

    public Patient(String id, String name, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", appointments=" + (appointments != null ? appointments.size() : 0) +
                '}';
    }
}