package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public abstract class MedicalStaff {

    private String id;
    private String name;
    private String departmentId;
    private List<Appointment> appointments = new ArrayList<>();

    public MedicalStaff() {}

    public MedicalStaff(String id, String name, String departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}
