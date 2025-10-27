package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String id;
    private String name;
    private List<Appointment>appointments;

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
    public List<Appointment> getAppointments(){
        return appointments;
    }
    public void setAppointments(){
        this.appointments = appointments;
    }
}