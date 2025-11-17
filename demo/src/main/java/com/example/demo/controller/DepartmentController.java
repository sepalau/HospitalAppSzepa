package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.readAll());
        return "department/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/form";
    }

    @PostMapping
    public String createDepartment(@ModelAttribute Department department) {
        departmentService.create(department);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("department", departmentService.read(id));
        return "department/form";
    }

    @PostMapping("/{id}/update")
    public String updateDepartment(@PathVariable String id, @ModelAttribute Department department) {
        departmentService.update(id, department);
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}
