package com.example.demo.repository;

import com.example.demo.model.MedicalStaff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class MedicalStaffRepository {

    private final List<MedicalStaff> staff = new ArrayList<MedicalStaff>();

    public void create(MedicalStaff medicalStaff) {
        staff.add(medicalStaff);
    }

    public List<MedicalStaff> readAll() {
        return staff;
    }

    public MedicalStaff findById(String id) {
        for (MedicalStaff s : staff) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public void update(String id, MedicalStaff updatedStaff) {
        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).getId().equals(id)) {
                staff.set(i, updatedStaff);
                return;
            }
        }
    }

    public void delete(String id) {
        Iterator<MedicalStaff> iterator = staff.iterator();
        while (iterator.hasNext()) {
            MedicalStaff s = iterator.next();
            if (s.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}