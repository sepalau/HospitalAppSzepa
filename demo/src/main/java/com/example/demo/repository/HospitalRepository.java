package com.example.demo.repository;

import com.example.demo.model.Hospital;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class HospitalRepository {

    private final List<Hospital> hospitals = new ArrayList<>();

    public void create(Hospital hospital) {
        hospitals.add(hospital);
    }

    public List<Hospital> readAll() {
        return hospitals;
    }

    public Hospital findById(String id) {
        return hospitals.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(String id, Hospital updatedHospital) {
        for (int i = 0; i < hospitals.size(); i++) {
            if (hospitals.get(i).getId().equals(id)) {
                hospitals.set(i, updatedHospital);
                return;
            }
        }
    }

    public void delete(String id) {
        hospitals.removeIf(h -> h.getId().equals(id));
    }
}
