package com.example.demo.repository;

import com.example.demo.model.Appointment;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AppointmentRepository extends InFileRepository<Appointment> {
    public AppointmentRepository() {
        super("appointment.json", new TypeReference<>() {}, Appointment::getId);
    }
}
