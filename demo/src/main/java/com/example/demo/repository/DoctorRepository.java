package com.example.demo.repository;

import com.example.demo.model.Doctor;
import java.util.*;

public class DoctorRepository {
    private final Map<String, Doctor> doctors = new HashMap<>();

    public void save(Doctor doctor) {
        doctors.put(doctor.getId(), doctor);
    }

    public List<Doctor> findAll() {
        return new ArrayList<>(doctors.values());
    }

    public Doctor findById(String id) {
        return doctors.get(id);
    }

    public void delete(String id) {
        doctors.remove(id);
    }
}
