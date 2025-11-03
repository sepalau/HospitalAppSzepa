package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public void create(Department department) {
        departmentRepository.create(department);
    }

    public List<Department> readAll() {
        return departmentRepository.readAll();
    }

    public Department findById(String id) {
        return departmentRepository.findById(id);
    }

    public void update(String id, Department updatedDepartment) {
        departmentRepository.update(id, updatedDepartment);
    }

    public void delete(String id) {
        departmentRepository.delete(id);
    }
}
