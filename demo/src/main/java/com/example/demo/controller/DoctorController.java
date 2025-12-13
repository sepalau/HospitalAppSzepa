package com.example.demo.controller;

import com.example.demo.model.Doctor;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    // 1. Listare cu Filtrare și Sortare
    @GetMapping
    public String list(
            Model model,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "sortField", defaultValue = "name") String sortField,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir
    ) {
        List<Doctor> list = doctorService.getAll(keyword, sortField, sortDir);
        model.addAttribute("doctors", list);

        // Parametrii UI
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "doctor/index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentService.getAll());
        return "doctor/form";
    }

    // 2. Salvare cu Validare
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("doctor") Doctor doctor,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAll());
            return "doctor/form";
        }
        try {
            doctorService.save(doctor);
        } catch (RuntimeException e) {
            result.rejectValue("licenseNumber", "error.doctor", e.getMessage());
            model.addAttribute("departments", departmentService.getAll());
            return "doctor/form";
        }
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getById(id));
        model.addAttribute("departments", departmentService.getAll());
        return "doctor/form";
    }

    // 3. Ștergere cu Protecție
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            doctorService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Doctor deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete doctor! They have assigned Appointments.");
        }
        return "redirect:/doctors";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getById(id));
        return "doctor/details";
    }
}