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

    public Doctor read(String id) {
        return repo.findById(id);
    }

    public void create(Doctor doctor) {
        repo.create(doctor);
    }

    public void update(String id, Doctor doctor) {
        repo.update(id, doctor);
    }

    public void delete(String id) {
        repo.delete(id);
    }
}