package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @NotBlank(message = "ID is required.")
    private String id;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Department ID is required.")
    private String departmentId;

    @NotBlank(message = "License Number is required.")
    private String licenseNumber;

    public Doctor() {}

    public Doctor(String id, String name, String departmentId, String licenseNumber) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.licenseNumber = licenseNumber;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
}
