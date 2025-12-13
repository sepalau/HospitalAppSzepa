package com.example.demo.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AppointmentForm {

    private Long id;

    @NotNull(message = "Trebuie să selectați un pacient!")
    private Long patientId;

    @NotNull(message = "Trebuie să selectați un departament!")
    private Long departmentId;

    @NotNull(message = "Trebuie să selectați un medic!")
    private Long medicalStaffId;

    // --- ADĂUGAT PENTRU ROOM ---
    @NotNull(message = "Trebuie să selectați o cameră!")
    private Long roomId;
    // ---------------------------

    @NotNull(message = "Data este obligatorie!")
    @Future(message = "Data programării trebuie să fie în viitor!") // Validare conform PDF [cite: 83]
    private LocalDate admissionDate;

    @NotBlank(message = "Statusul este obligatoriu!")
    private String status;
}