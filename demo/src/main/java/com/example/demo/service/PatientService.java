package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient getById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));
    }

    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    // --- METODA NOUĂ PENTRU SORTARE & FILTRARE ---
    public List<Patient> searchPatients(String name, String sortBy, String sortDir) {

        // 1. Direcția sortării
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        // 2. Obiectul Sort
        Sort sort = Sort.by(direction, sortBy);

        // 3. Apel repository (tratăm stringul gol ca fiind null pentru SQL)
        String filterName = (name != null && !name.trim().isEmpty()) ? name.trim() : null;

        return patientRepository.findPatientsWithFilters(filterName, sort);
    }
}