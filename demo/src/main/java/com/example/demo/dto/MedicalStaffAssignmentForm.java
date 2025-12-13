package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicalStaffAssignmentForm {

    private Long id;

    // Validare: ID-ul personalului medical este obligatoriu
    @NotNull(message = "Trebuie să selectați un cadru medical!")
    private Long staffId;

    // Validare: ID-ul programării este obligatoriu
    @NotNull(message = "Trebuie să selectați o programare!")
    private Long appointmentId;
}