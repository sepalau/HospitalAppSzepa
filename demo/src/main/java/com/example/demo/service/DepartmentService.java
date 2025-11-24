package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) {
        this.repo = repo;
    }

    public List<Department> readAll() {
        return repo.findAll();
    }

    public Department findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void create(Department d) {
        repo.save(d);
    }

    public void update(String id, Department d) {
        repo.save(d);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
