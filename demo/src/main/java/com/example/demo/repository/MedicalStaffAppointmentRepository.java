package com.example.demo.repository;

import com.example.demo.model.MedicalStaffAppointment;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MedicalStaffAppointmentRepository extends InFileRepository<MedicalStaffAppointment> {
    public MedicalStaffAppointmentRepository() {
        super("medicalstaffappointment.json", new TypeReference<>() {}, MedicalStaffAppointment::getId);
    }
}
