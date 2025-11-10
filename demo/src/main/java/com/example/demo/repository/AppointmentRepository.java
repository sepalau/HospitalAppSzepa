package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AppointmentRepository extends InFileRepository<Appointment> {
    public AppointmentRepository() {
        super("src/main/resources/data/appointments.json", Appointment.class);
    }

    public List<Appointment> findByPatientId(String patientId) {
        return findAll().stream()
                .filter(a -> patientId.equalsIgnoreCase(a.getPatientId()))
                .toList();
    }

    public List<Appointment> findByDepartmentId(String departmentId) {
        return findAll().stream()
                .filter(a -> departmentId.equalsIgnoreCase(a.getDepartmentId()))
                .toList();
    }

    public List<Appointment> findByStatus(String status) {
        return findAll().stream()
                .filter(a -> status.equalsIgnoreCase(a.getStatus()))
                .toList();
    }
}