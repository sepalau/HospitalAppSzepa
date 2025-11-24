package com.example.demo.model;

import com.example.demo.enums.RoomStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    private int capacity;

    private int number;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
