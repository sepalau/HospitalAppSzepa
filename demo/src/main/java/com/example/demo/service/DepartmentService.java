package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repo;

    public List<Department> getAll() {
        return repo.findAll();
    }

    public Department getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found: " + id));
    }

    public Department save(Department department) {
        return repo.save(department);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
