package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Doctor extends MedicalStaff {


    @NotBlank(message = "Numărul de licență este obligatoriu!")
    @Size(min = 3, message = "Licența trebuie să aibă minim 3 caractere.")
    private String licenseNumber;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}