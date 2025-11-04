package com.example.demo.repository;

import com.example.demo.model.MedicalStaffAppointment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalStaffAppointmentRepository {

    private final List<MedicalStaffAppointment> appointments = new ArrayList<>();

    public void create(MedicalStaffAppointment appointment) {
        appointments.add(appointment);
    }

    public List<MedicalStaffAppointment> readAll() {
        return appointments;
    }

    public MedicalStaffAppointment findById(String id) {
        return appointments.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    public void delete(String id) {
        appointments.removeIf(a -> a.getId().equals(id));
    }
}
