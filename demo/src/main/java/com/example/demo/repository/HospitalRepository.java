package com.example.demo.repository;

import com.example.demo.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    // Căutăm un spital după nume și oraș
    Optional<Hospital> findByNameAndCity(String name, String city);
}