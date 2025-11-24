package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repo;

    public List<Patient> findAll() {
        return repo.findAll();
    }

    public Patient getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found: " + id));
    }

    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}