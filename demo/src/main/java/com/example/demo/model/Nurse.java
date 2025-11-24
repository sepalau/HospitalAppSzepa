package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Nurse extends MedicalStaff {

    @NotBlank(message = "Qualification level is required")
    private String qualificationLevel; // Ex: "Registered Nurse"
}