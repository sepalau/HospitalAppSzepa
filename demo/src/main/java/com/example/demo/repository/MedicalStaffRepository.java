package com.example.demo.repository;

import com.example.demo.model.MedicalStaff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalStaffRepository {

    private final List<MedicalStaff> staffList = new ArrayList<>();

    public void create(MedicalStaff staff) {
        staffList.add(staff);
    }

    public List<MedicalStaff> readAll() {
        return staffList;
    }

    public MedicalStaff findById(String id) {
        return staffList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void delete(String id) {
        staffList.removeIf(s -> s.getId().equals(id));
    }
}
