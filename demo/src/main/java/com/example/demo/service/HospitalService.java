package com.example.demo.service;

import com.example.demo.model.Hospital;
import com.example.demo.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repo.save(hospital);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
