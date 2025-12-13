package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repo;

    public List<Doctor> getAll() {
        return repo.findAll();
    }

    public Doctor getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + id));
    }

    public void save(Doctor doctor) {
        // --- VALIDARE BUSINESS: UNICITATE LICENȚĂ ---
        Optional<Doctor> existing = repo.findByLicenseNumber(doctor.getLicenseNumber());

        if (existing.isPresent()) {
            // Verificăm dacă doctorul găsit este diferit de cel pe care îl edităm
            // (id null = creare nouă, id diferit = conflict cu alt doctor)
            if (doctor.getId() == null || !existing.get().getId().equals(doctor.getId())) {
                throw new RuntimeException("Acest număr de licență (" + doctor.getLicenseNumber() + ") există deja!");
            }
        }

        repo.save(doctor);
    }

    public void delete(Long id) {
        // Logica de bază de date va arunca eroare dacă există programări (Foreign Key)
        // Această eroare este prinsă în Controller
        repo.deleteById(id);
    }
}