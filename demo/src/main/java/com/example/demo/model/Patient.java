package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @NotBlank(message = "ID cannot be empty")
    private String id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    public Patient() {}

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}