package com.example.demo.repository;

import com.example.demo.model.Doctor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DoctorRepository extends InFileRepository<Doctor> {
    public DoctorRepository() {
        super("src/main/resources/data/doctors.json", Doctor.class);
    }

    public Doctor findByLicenseNumber(String licenseNumber) {
        return findAll().stream()
                .filter(d -> licenseNumber.equalsIgnoreCase(d.getLicenseNumber()))
                .findFirst()
                .orElse(null);
    }

    public List<Doctor> findByDepartment(String departmentId) {
        return findAll().stream()
                .filter(d -> departmentId.equalsIgnoreCase(d.getDepartmentId()))
                .toList();
    }
}