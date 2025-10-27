package com.example.demo.repository;

import com.example.demo.model.MedicalStaff;
import java.util.*;

public class MedicalStaffRepository {
    private final Map<String, MedicalStaff> staff = new HashMap<>();

    public void save(MedicalStaff person) {
        staff.put(person.getId(), person);
    }

    public List<MedicalStaff> findAll() {
        return new ArrayList<>(staff.values());
    }

    public MedicalStaff findById(String id) {
        return staff.get(id);
    }

    public void delete(String id) {
        staff.remove(id);
    }
}
