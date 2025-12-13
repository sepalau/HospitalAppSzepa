package com.example.demo.repository;

import com.example.demo.model.MedicalStaffAppointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalStaffAppointmentRepository extends JpaRepository<MedicalStaffAppointment, Long> {
    // Verificăm dacă există deja o legătură între acest medic și această programare
    boolean existsByAppointmentIdAndMedicalStaffId(Long appointmentId, Long medicalStaffId);
    @Query("SELECT msa FROM MedicalStaffAppointment msa WHERE " +
            "(:staffName IS NULL OR lower(msa.medicalStaff.name) LIKE lower(concat('%', :staffName, '%'))) AND " +
            "(:appointmentId IS NULL OR msa.appointment.id = :appointmentId)")
    List<MedicalStaffAppointment> findWithFilters(
            @Param("staffName") String staffName,
            @Param("appointmentId") Long appointmentId,
            Sort sort);
}