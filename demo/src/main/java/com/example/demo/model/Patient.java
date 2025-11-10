package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String id;
    private String name;
    private List<Appointment> appointments = new ArrayList<>();

    public Patient() {}

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}
