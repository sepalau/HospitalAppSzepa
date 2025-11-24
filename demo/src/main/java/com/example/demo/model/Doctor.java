package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Doctor extends MedicalStaff {

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @NotBlank(message = "License number is required")
    private String licenseNumber;

    @Override
    public String toString() {
        return "Doctor{id=" + getId() +
                ", name='" + getName() + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                '}';
    }
}
