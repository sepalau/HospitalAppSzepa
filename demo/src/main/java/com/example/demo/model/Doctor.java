package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Doctor extends MedicalStaff {

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @NotBlank(message = "License number is required")
    private String licenseNumber;
}
