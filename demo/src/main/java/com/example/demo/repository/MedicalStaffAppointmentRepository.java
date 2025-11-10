package com.example.demo.repository;

import com.example.demo.model.MedicalStaffAppointment;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MedicalStaffAppointmentRepository extends InFileRepository<MedicalStaffAppointment> {
    public MedicalStaffAppointmentRepository() {
        super("src/main/resources/data/medicalStaffAppointments.json", MedicalStaffAppointment.class);
    }

    public List<MedicalStaffAppointment> findByAppointmentId(String appointmentId) {
        return findAll().stream()
                .filter(msa -> appointmentId.equalsIgnoreCase(msa.getAppointmentId()))
                .toList();
    }

    public List<MedicalStaffAppointment> findByMedicalStaffId(String staffId) {
        return findAll().stream()
                .filter(msa -> staffId.equalsIgnoreCase(msa.getMedicalStaffId()))
                .toList();
    }
}