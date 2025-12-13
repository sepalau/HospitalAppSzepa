package com.example.demo.repository;

import com.example.demo.enums.QualificationLevel;
import com.example.demo.model.Department;
import com.example.demo.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {

    // Metodă lungă dar puternică: Caută după toate cele 3 criterii
    Optional<Nurse> findByNameAndQualificationLevelAndDepartment(
            String name,
            QualificationLevel qualificationLevel,
            Department department
    );
}