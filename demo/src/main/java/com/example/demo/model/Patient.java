package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    // AM ADAUGAT ACEST CAMP PENTRU CA IL AI IN FORMULAR
    private String address;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
}