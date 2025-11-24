package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.model.Hospital;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "department/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("hospitals", hospitalService.getAll());
        return "department/form";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("department") Department department,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("hospitals", hospitalService.getAll());
            return "department/form";
        }

        departmentService.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        model.addAttribute("hospitals", hospitalService.getAll());
        return "department/form";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("department") Department department,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("hospitals", hospitalService.getAll());
            return "department/form";
        }

        department.setId(id);
        departmentService.save(department);
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        return "department/details";
    }
}
