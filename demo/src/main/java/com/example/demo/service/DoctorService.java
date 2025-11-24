package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public List<Doctor> readAll() {
        return repo.findAll();
    }

    public Doctor findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void create(Doctor doctor) {
        repo.save(doctor);
    }

    public void update(String id, Doctor doctor) {
        repo.save(doctor);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
