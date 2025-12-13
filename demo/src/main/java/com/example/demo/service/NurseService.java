package com.example.demo.service;

import com.example.demo.enums.QualificationLevel;
import com.example.demo.model.Nurse;
import com.example.demo.repository.NurseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NurseService {

    private final NurseRepository repo;

    public List<Nurse> getAll() {
        return repo.findAll();
    }

    // --- CORECTURA AICI ---
    // Returnăm direct Optional, astfel Controller-ul poate folosi .orElseThrow()
    public Optional<Nurse> getById(Long id) {
        return repo.findById(id);
    }

    // --- SALVARE CU VALIDARE DE UNICITATE ---
    public void save(Nurse nurse) {
        // ATENȚIE: Această metodă trebuie să existe în NurseRepository (vezi mai jos)
        Optional<Nurse> existing = repo.findByNameAndQualificationLevelAndDepartment(
                nurse.getName(),
                nurse.getQualificationLevel(),
                nurse.getDepartment()
        );

        if (existing.isPresent()) {
            // Verificăm conflictul:
            // Dacă ID-ul este null (creare) SAU ID-ul este diferit (editare altă persoană)
            if (nurse.getId() == null || !existing.get().getId().equals(nurse.getId())) {
                throw new RuntimeException("Există deja o asistentă cu acest Nume, Calificare și Departament!");
            }
        }

        repo.save(nurse);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Nurse> searchNurses(String name, QualificationLevel level, String sortBy, String sortDir) {
        // 1. Configurare direcție sortare
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        // 2. Configurare câmp sortare
        Sort sort = Sort.by(direction, sortBy);

        // 3. Curățare parametri (string gol -> null)
        String filterName = (name != null && !name.trim().isEmpty()) ? name.trim() : null;

        // 4. Apel repository
        return repo.findNursesWithFilters(filterName, level, sort);
    }
}