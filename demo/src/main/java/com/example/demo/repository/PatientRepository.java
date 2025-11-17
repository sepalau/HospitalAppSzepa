package com.example.demo.repository;

import com.example.demo.model.Patient;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepository extends InFileRepository<Patient> {
    public PatientRepository() {
        super("patient.json", new TypeReference<>() {}, Patient::getId);
    }
}
