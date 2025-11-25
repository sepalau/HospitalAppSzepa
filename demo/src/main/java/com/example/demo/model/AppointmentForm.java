package com.example.demo.model;

import lombok.Data;

@Data
public class AppointmentForm {

    private Long id;

    private Long patientId;
    private Long departmentId;
    private Long medicalStaffId;

    private String admissionDate;
    private String status;
}
