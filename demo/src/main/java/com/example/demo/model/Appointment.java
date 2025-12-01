package com.example.demo.model;

import com.example.demo.enums.AppointmentStatus;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // In diagrama e String, dar in MySQL e Long (Best Practice)

    // Atribut din diagrama: admissionDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissionDate;

    // Atribut din diagrama: Status
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    // --- RELATII (Bazate pe liniile din diagrama) ---
    // Diagrama are "String PatientId" -> In JPA devine Obiectul Patient
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Diagrama are "String DepartmentId" -> In JPA devine Obiectul Department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Diagrama are "List MedicalStaff", dar pentru simplitate in Formular incepem cu UNUL singur
    // (Relatie ManyToOne catre MedicalStaff)
    @ManyToOne
    @JoinColumn(name = "medical_staff_id")
    private MedicalStaff medicalStaff;

    public Appointment() {}

    // Getters si Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public MedicalStaff getMedicalStaff() { return medicalStaff; }
    public void setMedicalStaff(MedicalStaff medicalStaff) { this.medicalStaff = medicalStaff; }
}