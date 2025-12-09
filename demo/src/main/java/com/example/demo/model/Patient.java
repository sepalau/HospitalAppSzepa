package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Numele este obligatoriu!")
    @Size(min = 3, max = 50, message = "Numele trebuie să aibă între 3 și 50 de caractere.")
    private String name;


    @NotBlank(message = "Adresa este obligatorie!")
    @Size(min = 5, message = "Adresa este prea scurtă (minim 5 caractere).")
    private String address;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
}