package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository = new DepartmentRepository();

    @PostMapping
    public String create(@RequestBody Department department) {
        departmentRepository.create(department);
        return "Department created successfully.";
    }

    @GetMapping
    public List<Department> readAll() {
        return departmentRepository.readAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable String id) {
        return departmentRepository.findById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Department updatedDepartment) {
        departmentRepository.update(id, updatedDepartment);
        return "Department updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        departmentRepository.delete(id);
        return "Department deleted successfully.";
    }
}
