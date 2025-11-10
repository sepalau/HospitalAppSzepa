package com.example.demo.repository;

import com.example.demo.model.Hospital;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class HospitalRepository extends InFileRepository<Hospital> {
    public HospitalRepository() {
        super("src/main/resources/data/hospitals.json", Hospital.class);
    }

    public Hospital findByName(String name) {
        return findAll().stream()
                .filter(h -> name.equalsIgnoreCase(h.getName()))
                .findFirst()
                .orElse(null);
    }

    public List<Hospital> findByCity(String city) {
        return findAll().stream()
                .filter(h -> city.equalsIgnoreCase(h.getCity()))
                .toList();
    }
}