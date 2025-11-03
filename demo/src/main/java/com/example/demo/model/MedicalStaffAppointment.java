package com.example.demo.model;

import java.time.LocalDateTime;

public class MedicalStaffAppointment {
    private String id;
    private String doctorName;
    private String patientName;
    private LocalDateTime appointmentDateTime;

    // Constructor gol pentru Spring & Thymeleaf
    public MedicalStaffAppointment() {}

    // Constructor complet
    public MedicalStaffAppointment(String id, String doctorName, String patientName, LocalDateTime appointmentDateTime) {
        this.id = id;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.appointmentDateTime = appointmentDateTime;
    }

    // Getters și setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
}
