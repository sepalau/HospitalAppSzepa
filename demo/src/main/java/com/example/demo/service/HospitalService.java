package com.example.demo.service;

import com.example.demo.model.Hospital;
import com.example.demo.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository repo;

    public List<Hospital> getAll() {
        return repo.findAll();
    }

    public Hospital getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital not found: " + id));
    }

    public Hospital save(Hospital hospital) {
        // --- VALIDARE BUSINESS: UNICITATE ---
        // Verificăm dacă există deja un spital cu același nume în același oraș
        Optional<Hospital> existing = repo.findByNameAndCity(hospital.getName(), hospital.getCity());

        if (existing.isPresent()) {
            // Dacă am găsit unul, verificăm să nu fie chiar cel pe care îl edităm
            if (hospital.getId() == null || !existing.get().getId().equals(hospital.getId())) {
                throw new RuntimeException("Există deja un spital cu numele '" + hospital.getName() +
                        "' în orașul '" + hospital.getCity() + "'!");
            }
        }

        return repo.save(hospital);
    }

    public void delete(Long id) {
        // Ne bazăm pe eroarea de Foreign Key din baza de date, prinsă de Controller
        repo.deleteById(id);
    }
}