package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repo;

    // --- SORTARE ȘI FILTRARE (PDF 5) ---
    public List<Department> getAll(String keyword, String sortField, String sortDir) {
        // 1. Stabilim direcția sortării
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        // 2. Filtrare (Search)
        if (keyword != null && !keyword.isEmpty()) {
            return repo.searchByKeyword(keyword, sort);
        }

        // 3. Returnăm tot, sortat
        return repo.findAll(sort);
    }

    // Metoda veche (pentru compatibilitate)
    public List<Department> getAll() {
        return repo.findAll();
    }

    public Department getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found: " + id));
    }

    public void save(Department department) {
        // Validare Unicitate Nume în Spital
        if (department.getId() == null) {
            Optional<Department> existing = repo.findByNameAndHospitalId(department.getName(), department.getHospital().getId());
            if (existing.isPresent()) {
                throw new RuntimeException("Există deja un departament cu acest nume în acest spital!");
            }
        }
        repo.save(department);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}