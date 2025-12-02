package com.example.demo.controller;

import com.example.demo.model.Doctor;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "doctor/index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentService.getAll());
        return "doctor/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getById(id));
        model.addAttribute("departments", departmentService.getAll());
        return "doctor/form";
    }

    // --- AICI ESTE FIX-UL PENTRU EROAREA 500 ---
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            doctorService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Doctor deleted successfully!");
        } catch (Exception e) {
            // Prindem eroarea dacă doctorul are programări
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