package com.example.demo.model;

import com.example.demo.enums.AppointmentStatus;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.demo.model.Room;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissionDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    @ManyToOne
    @JoinColumn(name = "medical_staff_id")
    private MedicalStaff medicalStaff;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Appointment() {}

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

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
}