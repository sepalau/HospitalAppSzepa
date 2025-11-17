package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void create(Patient patient) {
        patientRepository.create(patient);
    }

    public List<Patient> readAll() {
        return patientRepository.findAll();
    }

    public Patient findById(String id) {
        return patientRepository.findById(id);
    }

    public void update(String id, Patient updatedPatient) {
        patientRepository.update(id, updatedPatient);
    }

    public void delete(String id) {
        patientRepository.delete(id);
    }
}
