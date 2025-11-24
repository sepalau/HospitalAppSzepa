package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public List<Patient> readAll() {
        return repo.findAll();
    }

    public Patient findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void create(Patient patient) {
        repo.save(patient);
    }

    public void update(String id, Patient patient) {
        repo.save(patient);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}