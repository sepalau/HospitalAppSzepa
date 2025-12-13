package com.example.demo.repository;

import com.example.demo.enums.QualificationLevel;
import com.example.demo.model.Department;
import com.example.demo.model.Nurse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {

    // --- METODA NOUĂ NECESARĂ PENTRU VALIDARE ---
    // Spring Data JPA va genera automat query-ul bazat pe numele metodei
    Optional<Nurse> findByNameAndQualificationLevelAndDepartment(
            String name,
            QualificationLevel qualificationLevel,
            Department department
    );

    // Metoda de filtrare existentă
    @Query("SELECT n FROM Nurse n WHERE " +
            "(:name IS NULL OR lower(n.name) LIKE lower(concat('%', :name, '%'))) AND " +
            "(:level IS NULL OR n.qualificationLevel = :level)")
    List<Nurse> findNursesWithFilters(
            @Param("name") String name,
            @Param("level") QualificationLevel level,
            Sort sort);
}