package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    // 1. Listare cu Filtrare și Sortare
    @GetMapping
    public String index(
            Model model,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "sortField", defaultValue = "name") String sortField, // Sortare implicită după nume
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir
    ) {
        List<Department> list = departmentService.getAll(keyword, sortField, sortDir);
        model.addAttribute("departments", list);

        // Parametrii pentru UI (pentru a păstra starea filtrelor)
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "department/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("hospitals", hospitalService.getAll());
        return "department/form";
    }

    // 2. Salvare cu Validare
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Department department,
                       BindingResult result, Model model) {

        // A. Validare Format (câmpuri goale)
        if (result.hasErrors()) {
            model.addAttribute("hospitals", hospitalService.getAll());
            return "department/form";
        }

        try {
            // B. Validare Business (Unicitate)
            departmentService.save(department);
        } catch (RuntimeException e) {
            // Dacă există deja, afișăm eroarea pe câmpul 'name'
            result.rejectValue("name", "error.department", e.getMessage());
            model.addAttribute("hospitals", hospitalService.getAll());
            return "department/form";
        }

        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        model.addAttribute("hospitals", hospitalService.getAll());
        return "department/form";
    }

    // 3. Ștergere cu Protecție
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