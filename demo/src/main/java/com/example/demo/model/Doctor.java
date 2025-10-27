package com.example.demo.model;

import java.util.List;

public class Doctor extends MedicalStaff {

    private String licenseNumber;

    public Doctor() {
        super();
    }

    public Doctor(String id, String name, String departmentId,
                  List<Appointment> appointments, String licenseNumber) {
        super(id, name, departmentId, appointments);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", departmentId='" + getDepartmentId() + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", appointments=" + (getAppointments() != null ? getAppointments().size() : 0) +
                '}';
    }
}