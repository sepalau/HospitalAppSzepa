package com.example.demo.repository;

import com.example.demo.model.Patient;
import java.util.*;

public class PatientRepository {
    private final Map<String, Patient> patients = new HashMap<>();

    public void save(Patient patient) {
        patients.put(patient.getId(), patient);
    }

    public List<Patient> findAll() {
        return new ArrayList<>(patients.values());
    }

    public Patient findById(String id) {
        return patients.get(id);
    }

    public void delete(String id) {
        patients.remove(id);
    }
}
