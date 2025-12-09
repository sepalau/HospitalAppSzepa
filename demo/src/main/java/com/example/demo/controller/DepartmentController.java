package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // IMPORT NECESAR

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "department/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("hospitals", hospitalService.getAll());
        return "department/form";
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
        return "department/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            departmentService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Department deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete department! It contains Doctors, Nurses or Rooms.");
        }
        return "redirect:/departments";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        return "department/details";
    }
}