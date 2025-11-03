package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public void create(Doctor doctor) {
        doctorRepository.create(doctor);
    }

    public List<Doctor> readAll() {
        return doctorRepository.readAll();
    }

    public Doctor findById(String id) {
        return doctorRepository.findById(id);
    }

    public void update(String id, Doctor updatedDoctor) {
        doctorRepository.update(id, updatedDoctor);
    }

    public void delete(String id) {
        doctorRepository.delete(id);
    }
}