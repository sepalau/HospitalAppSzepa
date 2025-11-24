package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repo;

    public AppointmentService(AppointmentRepository repo) {
        this.repo = repo;
    }

    public List<Appointment> readAll() {
        return repo.findAll();
    }

    public Appointment findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void create(Appointment a) {
        repo.save(a);
    }

    public void update(String id, Appointment a) {
        repo.save(a);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
