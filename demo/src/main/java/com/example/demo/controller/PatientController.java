package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("patients", patientService.getAll());
        return "patient/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("patient") Patient patient,
            BindingResult result
    ) {
        if (result.hasErrors()) return "patient/form";

        patientService.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getById(id));
        return "patient/form";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("patient") Patient patient,
            BindingResult result
    ) {
        if (result.hasErrors()) return "patient/form";

        patient.setId(id);
        patientService.save(patient);
        return "redirect:/patients";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        patientService.delete(id);
        return "redirect:/patients";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getById(id));
        return "patient/details";
    }
}
