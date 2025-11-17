package com.example.demo.repository;

import com.example.demo.model.Hospital;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.nio.file.Paths;

@Repository
public class HospitalRepository extends InFileRepository<Hospital> {
    public HospitalRepository() {
        super("hospital.json", new TypeReference<>() {}, Hospital::getId);
        System.out.println("LOADING FROM: " + Paths.get("data/hospital.json").toAbsolutePath());

    }
}
