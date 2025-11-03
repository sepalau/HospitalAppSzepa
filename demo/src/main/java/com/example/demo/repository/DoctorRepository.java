package com.example.demo.repository;

import com.example.demo.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class DoctorRepository {

    private final List<Doctor> doctors = new ArrayList<Doctor>();

    public void create(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> readAll() {
        return doctors;
    }

    public Doctor findById(String id) {
        for (Doctor d : doctors) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    public void update(String id, Doctor updatedDoctor) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().equals(id)) {
                doctors.set(i, updatedDoctor);
                return;
            }
        }
    }

    public void delete(String id) {
        Iterator<Doctor> iterator = doctors.iterator();
        while (iterator.hasNext()) {
            Doctor d = iterator.next();
            if (d.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
