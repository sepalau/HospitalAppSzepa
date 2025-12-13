package com.example.demo.repository;

import com.example.demo.model.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByNameAndHospitalId(String name, Long hospitalId);

    // Căutare după Numele Departamentului sau Numele Spitalului (Case Insensitive)
    @Query("SELECT d FROM Department d WHERE " +
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(d.hospital.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Department> searchByKeyword(String keyword, Sort sort);
}