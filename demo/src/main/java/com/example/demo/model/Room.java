package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @NotBlank(message = "ID cannot be empty")
    private String id;

    @NotBlank(message = "Hospital ID cannot be empty")
    private String hospitalId;

    @Positive(message = "Capacity must be a positive number")
    private double capacity;

    @NotBlank(message = "Room number cannot be empty")
    private String number;

    @NotBlank(message = "Status cannot be empty")
    private String status; // Available / Occupied

    public Room() {}

    public Room(String id, String hospitalId, double capacity, String number, String status) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.capacity = capacity;
        this.number = number;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getHospitalId() { return hospitalId; }
    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }

    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}