package com.example.demo.model;

import com.example.demo.enums.QualificationLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Nurse extends MedicalStaff {


    @NotNull(message = "Nivelul de calificare este obligatoriu!")
    @Enumerated(EnumType.STRING)
    private QualificationLevel qualificationLevel;

    @NotNull(message = "Trebuie să selectați un departament!")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}