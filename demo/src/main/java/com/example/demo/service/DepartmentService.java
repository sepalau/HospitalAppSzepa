package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repo;

    public List<Department> getAll() {
        return repo.findAll();
    }

    public Department getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found: " + id));
    }

    public Department save(Department department) {
        // --- VALIDARE BUSINESS: UNICITATE ---
        // Verificăm dacă există deja un departament cu același nume în același spital
        if (department.getHospital() != null) {
            Optional<Department> existing = repo.findByNameAndHospitalId(
                    department.getName(),
                    department.getHospital().getId()
            );

            // Dacă am găsit unul, verificăm dacă nu cumva este chiar cel pe care îl edităm acum
            if (existing.isPresent()) {
                // Dacă ID-ul este null (creare nouă) SAU ID-ul e diferit (conflict cu altul)
                if (department.getId() == null || !existing.get().getId().equals(department.getId())) {
                    throw new RuntimeException("Un departament cu numele '" + department.getName() +
                            "' există deja în acest spital!");
                }
            }
        }

        return repo.save(department);
    }

    public void delete(Long id) {
        // Aici ne bazăm pe Foreign Key constraints din baza de date.
        // Dacă departamentul are doctori, baza de date va arunca o eroare,
        // care va fi prinsă de Controller (așa cum ai făcut deja).
        repo.deleteById(id);
    }
}