package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public String list(Model model) {
        // Trimitem lista de pacienți către HTML
        model.addAttribute("patients", patientService.getAll());
        return "patient/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Patient patient) {
        patientService.save(patient);
        return "redirect:/patients"; // Redirect la PLURAL
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getById(id));
        return "patient/form";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getById(id));
        return "patient/details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            patientService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Patient deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete patient. They might have existing appointments.");
        }
        return "redirect:/patients";
    }
}