package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "nurses")
public class Nurse extends MedicalStaff {

    @NotBlank(message = "Qualification level is required.")
    private String qualificationLevel; // Registered Nurse / Practical Nurse

    public Nurse() { super(); }

    public Nurse(String id, String name, String departmentId, String qualificationLevel) {
        super(id, name, departmentId);
        this.qualificationLevel = qualificationLevel;
    }

    public String getQualificationLevel() { return qualificationLevel; }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }
}