package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;

    public List<Appointment> getAll() {
        return repository.findAll();
    }

    public Appointment getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public Appointment save(Appointment appointment) {
        return repository.save(appointment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
