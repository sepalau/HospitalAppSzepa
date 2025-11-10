package com.example.demo.repository;

import com.example.demo.model.Department;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DepartmentRepository extends InFileRepository<Department> {
    public DepartmentRepository() {
        super("src/main/resources/data/departments.json", Department.class);
    }

    public List<Department> findByHospitalId(String hospitalId) {
        return findAll().stream()
                .filter(d -> hospitalId.equalsIgnoreCase(d.getHospitalId()))
                .toList();
    }
}