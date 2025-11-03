package com.example.demo.repository;

import com.example.demo.model.Patient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PatientRepository {

    private final List<Patient> patients = new ArrayList<Patient>();

    public void create(Patient patient) {
        patients.add(patient);
    }

    public List<Patient> readAll() {
        return patients;
    }

    public Patient findById(String id) {
        for (Patient p : patients) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void update(String id, Patient updatedPatient) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(id)) {
                patients.set(i, updatedPatient);
                return;
            }
        }
    }

    public void delete(String id) {
        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
