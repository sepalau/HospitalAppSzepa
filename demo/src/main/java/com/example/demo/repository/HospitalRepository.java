package com.example.demo.repository;

import com.example.demo.model.Hospital;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class HospitalRepository {

    private final List<Hospital> hospitals = new ArrayList<Hospital>();

    public void create(Hospital hospital) {
        hospitals.add(hospital);
    }

    public List<Hospital> readAll() {
        return hospitals;
    }

    public Hospital findById(String id) {
        for (Hospital h : hospitals) {
            if (h.getId().equals(id)) {
                return h;
            }
        }
        return null;
    }

    public void update(String id, Hospital updatedHospital) {
        for (int i = 0; i < hospitals.size(); i++) {
            Hospital current = hospitals.get(i);
            if (current.getId().equals(id)) {
                hospitals.set(i, updatedHospital);
                return;
            }
        }
    }

    public void delete(String id) {
        Iterator<Hospital> iterator = hospitals.iterator();
        while (iterator.hasNext()) {
            Hospital h = iterator.next();
            if (h.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
