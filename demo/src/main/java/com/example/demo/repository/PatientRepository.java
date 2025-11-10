package com.example.demo.repository;

import com.example.demo.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepository extends InFileRepository<Patient> {
    public PatientRepository() {
        super("src/main/resources/data/patients.json", Patient.class);
    }

    public Patient findByName(String name) {
        return findAll().stream()
                .filter(p -> name.equalsIgnoreCase(p.getName()))
                .findFirst()
                .orElse(null);
    }
}