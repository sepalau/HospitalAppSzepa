package com.example.demo.repository;

import com.example.demo.model.MedicalStaffAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalStaffAppointmentRepository extends JpaRepository<MedicalStaffAppointment, Long> {
}
