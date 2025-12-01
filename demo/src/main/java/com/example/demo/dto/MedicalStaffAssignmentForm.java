package com.example.demo.dto;

import lombok.Data;

@Data
public class MedicalStaffAssignmentForm {
    private Long id;
    private Long staffId;       // ID-ul Doctorului/Asistentei
    private Long appointmentId; // ID-ul Programării
}