package com.example.demo.repository;

import com.example.demo.model.Doctor;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorRepository extends InFileRepository<Doctor> {

    public DoctorRepository() {
        super("doctor.json", new TypeReference<>() {}, Doctor::getId);
    }
}