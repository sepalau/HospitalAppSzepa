package com.example.demo.repository;

import com.example.demo.model.Hospital;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findByNameAndCity(String name, String city);

    // Căutare după Nume sau Oraș (Case Insensitive)
    @Query("SELECT h FROM Hospital h WHERE " +
            "LOWER(h.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(h.city) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Hospital> searchByKeyword(String keyword, Sort sort);
}