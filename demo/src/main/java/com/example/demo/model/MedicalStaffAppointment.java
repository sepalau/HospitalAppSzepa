package com.example.demo.model;

public class MedicalStaffAppointment {
    private String id;
    private String appointmentId;
    private String medicalStaffId;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
    public String getMedicalStaffId() {
        return medicalStaffId;
    }
    public void setMedicalStaffId(String medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }
}
