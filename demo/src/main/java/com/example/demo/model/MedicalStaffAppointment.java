package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "medical_staff_appointments")
public class MedicalStaffAppointment {

    @Id
    @NotBlank(message = "ID is required.")
    private String id;

    @NotBlank(message = "Appointment ID is required.")
    private String appointmentId;

    @NotBlank(message = "Medical Staff ID is required.")
    private String medicalStaffId;

    public MedicalStaffAppointment() {}

    public MedicalStaffAppointment(String id, String appointmentId, String medicalStaffId) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.medicalStaffId = medicalStaffId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public String getMedicalStaffId() { return medicalStaffId; }
    public void setMedicalStaffId(String medicalStaffId) { this.medicalStaffId = medicalStaffId; }
}