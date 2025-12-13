package com.example.demo.repository;

import com.example.demo.model.MedicalStaffAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalStaffAppointmentRepository extends JpaRepository<MedicalStaffAppointment, Long> {
    // Verificăm dacă există deja o legătură între acest medic și această programare
    boolean existsByAppointmentIdAndMedicalStaffId(Long appointmentId, Long medicalStaffId);
}