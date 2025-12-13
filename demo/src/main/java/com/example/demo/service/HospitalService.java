package com.example.demo.service;

import com.example.demo.model.Hospital;
import com.example.demo.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository repo;

    // --- SORTARE ȘI FILTRARE ---
    public List<Hospital> getAll(String keyword, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        if (keyword != null && !keyword.isEmpty()) {
            return repo.searchByKeyword(keyword, sort);
        }

        return repo.findAll(sort);
    }

    // Metodă veche pentru compatibilitate
    public List<Hospital> getAll() {
        return repo.findAll();
    }

    public Hospital getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital not found: " + id));
    }

    public void save(Hospital hospital) {
        Optional<Hospital> existing = repo.findByNameAndCity(hospital.getName(), hospital.getCity());
        if (existing.isPresent()) {
            if (hospital.getId() == null || !existing.get().getId().equals(hospital.getId())) {
                throw new RuntimeException("Există deja un spital cu acest nume în acest oraș!");
            }
        }
        repo.save(hospital);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}