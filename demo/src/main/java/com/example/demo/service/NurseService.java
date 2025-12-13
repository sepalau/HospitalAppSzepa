package com.example.demo.service;

import com.example.demo.model.Nurse;
import com.example.demo.repository.NurseRepository;
import lombok.RequiredArgsConstructor;
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

    public Nurse getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nurse not found: " + id));
    }

    // --- SALVARE CU VALIDARE DE UNICITATE ---
    public void save(Nurse nurse) {
        // Căutăm în baza de date dacă există deja o combinație identică
        Optional<Nurse> existing = repo.findByNameAndQualificationLevelAndDepartment(
                nurse.getName(),
                nurse.getQualificationLevel(),
                nurse.getDepartment()
        );

        if (existing.isPresent()) {
            // Verificăm dacă nu cumva este chiar asistenta pe care o edităm acum
            // (Dacă ID-ul este null, e creare nouă -> Eroare)
            // (Dacă ID-ul este diferit, e conflict cu alta existentă -> Eroare)
            if (nurse.getId() == null || !existing.get().getId().equals(nurse.getId())) {
                throw new RuntimeException("Există deja o asistentă cu acest Nume, Calificare și Departament!");
            }
        }

        repo.save(nurse);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}