package com.example.demo.dto;

import lombok.Data;

@Data
public class MedicalStaffAssignmentForm {
    private Long id;
    private Long staffId;
    private Long appointmentId;
}