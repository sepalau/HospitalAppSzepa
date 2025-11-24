package com.example.demo.service;

import com.example.demo.model.Nurse;
import com.example.demo.repository.NurseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NurseService {
    private final NurseRepository repo;

    public List<Nurse> getAll() { return repo.findAll(); }

    public Nurse getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Nurse not found: " + id));
    }

    public Nurse save(Nurse nurse) { return repo.save(nurse); }

    public void delete(Long id) { repo.deleteById(id); }
}