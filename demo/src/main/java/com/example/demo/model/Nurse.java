package com.example.demo.model;

import com.example.demo.enums.QualificationLevel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Nurse extends MedicalStaff {

    @Enumerated(EnumType.STRING)
    private QualificationLevel qualificationLevel;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
