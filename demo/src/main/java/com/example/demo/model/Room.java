package com.example.demo.model;

import com.example.demo.enums.RoomStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive; //
import lombok.Data;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Spitalul este obligatoriu!")
    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @Positive(message = "Capacitatea trebuie să fie un număr pozitiv!") //
    private int capacity;

    @Positive(message = "Numărul camerei trebuie să fie pozitiv!") //
    @Min(value = 1, message = "Numărul camerei trebuie să fie minim 1")
    private int number;

    @NotNull(message = "Statusul camerei este obligatoriu!")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}