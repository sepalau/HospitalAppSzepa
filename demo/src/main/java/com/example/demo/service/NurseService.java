package com.example.demo.service;

import com.example.demo.model.Nurse;
import com.example.demo.repository.NurseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService {

    private final NurseRepository repo;

    public NurseService(NurseRepository repo) {
        this.repo = repo;
    }

    public List<Nurse> readAll() {
        return repo.findAll();
    }

    public Nurse findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void create(Nurse nurse) {
        repo.save(nurse);
    }

    public void update(String id, Nurse nurse) {
        repo.save(nurse);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}