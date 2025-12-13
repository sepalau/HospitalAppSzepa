package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED) // Sau SINGLE_TABLE, depinde cum ai setat baza
public class MedicalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // AICI ESTE DEFINIȚIA UNICĂ A NUMELUI
    @NotBlank(message = "Numele este obligatoriu!")
    @Size(min = 3, message = "Numele trebuie să aibă minim 3 caractere.")
    private String name;
}