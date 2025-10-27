package com.example.demo.repository;

import com.example.demo.model.MedicalStaffAppointment;
import java.util.*;

public class MedicalStaffAppointmentRepository {
    private final Map<String, MedicalStaffAppointment> appointments = new HashMap<>();

    public void save(MedicalStaffAppointment msa) {
        appointments.put(msa.getId(), msa);
    }

    public List<MedicalStaffAppointment> findAll() {
        return new ArrayList<>(appointments.values());
    }

    public MedicalStaffAppointment findById(String id) {
        return appointments.get(id);
    }

    public void delete(String id) {
        appointments.remove(id);
    }
}
