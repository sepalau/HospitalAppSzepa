package com.example.demo.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AppointmentForm {

    private Long id;

    // @NotNull asigură că utilizatorul a selectat ceva din dropdown
    @NotNull(message = "Trebuie să selectați un pacient!")
    private Long patientId;

    @NotNull(message = "Trebuie să selectați un departament!")
    private Long departmentId;

    @NotNull(message = "Trebuie să selectați un medic!")
    private Long medicalStaffId;

    // @NotNull = nu poate fi gol
    // @Future = data trebuie să fie în viitor (cerință PDF)
    @NotNull(message = "Data este obligatorie!")
    @Future(message = "Data programării trebuie să fie în viitor!")
    private LocalDate admissionDate;

    // @NotBlank = nu poate fi text gol
    @NotBlank(message = "Statusul este obligatoriu!")
    private String status;
}