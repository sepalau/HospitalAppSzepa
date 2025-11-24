package com.example.demo.service;

import com.example.demo.model.Hospital;
import com.example.demo.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository repo;

    public HospitalService(HospitalRepository repo) {
        this.repo = repo;
    }

    public List<Hospital> readAll() {
        return repo.findAll();
    }

    public Hospital findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void create(Hospital h) {
        repo.save(h);
    }

    public void update(String id, Hospital h) {
        repo.save(h);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
