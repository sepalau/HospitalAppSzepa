package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Doctor extends MedicalStaff {

    // Campul 'name' este mostenit din MedicalStaff.
    // Asigura-te ca in MedicalStaff.java ai @NotBlank pe name!

    @NotBlank(message = "Numărul de licență este obligatoriu!")
    @Size(min = 3, message = "Licența trebuie să aibă minim 3 caractere.")
    private String licenseNumber;

    @NotNull(message = "Trebuie să selectați un departament!")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}