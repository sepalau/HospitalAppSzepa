package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")  // ← URL-ul rămâne la plural, normal REST
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "department/index";   // ← TEMPLATE folder singular
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("hospitals", hospitalService.getAll());
        return "department/form";    // ← TEMPLATE folder singular
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Department department) {
        departmentService.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        model.addAttribute("hospitals", hospitalService.getAll());
        return "department/form";    // ← TEMPLATE folder singular
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}
