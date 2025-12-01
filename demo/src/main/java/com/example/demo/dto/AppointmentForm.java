package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentForm {
    private Long id;
    private Long patientId;
    private Long departmentId;
    private Long medicalStaffId; // poate fi null dacă nu ai tabel Staff
    private LocalDate admissionDate;
    private String status;
}
