package com.example.demo.repository;

import com.example.demo.model.Department;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository extends InFileRepository<Department> {
    public DepartmentRepository() {
        super("department.json", new TypeReference<>() {}, Department::getId);
    }
}