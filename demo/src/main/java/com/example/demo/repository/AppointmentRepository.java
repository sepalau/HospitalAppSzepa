package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Căutare după Nume Pacient sau Nume Departament (Case Insensitive) + Sortare
    @Query("SELECT a FROM Appointment a WHERE " +
            "LOWER(a.patient.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.department.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Appointment> searchByKeyword(String keyword, Sort sort);
}