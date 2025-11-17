package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> readAll() {
        return repository.findAll();
    }

    public Appointment findById(String id) {
        return repository.findById(id);
    }

    public void create(Appointment appointment) {
        repository.create(appointment);
    }

    public void update(String id, Appointment appointment) {
        repository.update(id, appointment);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
