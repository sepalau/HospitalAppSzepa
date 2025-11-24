package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repo;

    public List<Doctor> getAll() {
        return repo.findAll();
    }

    public Doctor getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + id));
    }

    public Doctor save(Doctor doctor) {
        return repo.save(doctor);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
