package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.AbstractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final AbstractRepository<Department> repo;

    public DepartmentService(AbstractRepository<Department> repo) {
        this.repo = repo;
    }

    public List<Department> readAll() {
        return repo.findAll();
    }

    public Department read(String id) {
        return repo.findById(id);
    }

    public void create(Department d) {
        repo.create(d);
    }

    public void update(String id, Department d) {
        repo.update(id, d);
    }

    public void delete(String id) {
        repo.delete(id);
    }
}
