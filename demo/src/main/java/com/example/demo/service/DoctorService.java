package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repo;

    // --- SORTARE ȘI FILTRARE ---
    public List<Doctor> getAll(String keyword, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        if (keyword != null && !keyword.isEmpty()) {
            return repo.searchByKeyword(keyword, sort);
        }

        return repo.findAll(sort);
    }

    // Metodă veche pentru compatibilitate
    public List<Doctor> getAll() {
        return repo.findAll();
    }

    public Doctor getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + id));
    }

    public void save(Doctor doctor) {
        Optional<Doctor> existing = repo.findByLicenseNumber(doctor.getLicenseNumber());
        if (existing.isPresent()) {
            if (doctor.getId() == null || !existing.get().getId().equals(doctor.getId())) {
                throw new RuntimeException("Acest număr de licență există deja!");
            }
        }
        repo.save(doctor);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}