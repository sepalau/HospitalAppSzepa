package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("patient", patientService.getAll());
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
        return "redirect:/patient";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getById(id));
        return "patient/details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        patientService.delete(id);
        return "redirect:/patient";
    }
}
