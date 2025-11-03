package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.readAll());
        return "department/index"; // matches templates/department/index.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/form"; // matches templates/department/form.html
    }

    @PostMapping
    public String createDepartment(@ModelAttribute Department department) {
        departmentService.create(department);
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}
