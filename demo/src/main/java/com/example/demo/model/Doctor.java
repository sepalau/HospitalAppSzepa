package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Doctor extends MedicalStaff {

    // Numele este moștenit din MedicalStaff, dar validarea trebuie pusă aici sau în clasa părinte.
    // Deoarece MedicalStaff e probabil simplă, putem pune validarea pe field-urile specifice aici sau suprascrie.

    // NOTA: Dacă 'name' e în MedicalStaff, asigură-te că MedicalStaff are @NotBlank pe name.
    // Dacă nu poți modifica MedicalStaff acum, ne concentrăm pe ce e specific Doctorului.

    @NotBlank(message = "Numărul de licență este obligatoriu!")
    @Size(min = 3, message = "Licența trebuie să aibă minim 3 caractere.")
    private String licenseNumber;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}