package com.example.demo.repository;

import com.example.demo.model.Patient;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE " +
            "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%')))")
    List<Patient> findPatientsWithFilters(@Param("name") String name, Sort sort);
}