package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_staff_appointments")
public class MedicalStaffAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "medical_staff_id")
    private MedicalStaff medicalStaff;

    public MedicalStaffAppointment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public MedicalStaff getMedicalStaff() { return medicalStaff; }
    public void setMedicalStaff(MedicalStaff medicalStaff) { this.medicalStaff = medicalStaff; }
}