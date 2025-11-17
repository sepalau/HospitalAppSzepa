package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.readAll());
        return "patient/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }

    @PostMapping
    public String createPatient(@ModelAttribute Patient patient) {
        patientService.create(patient);
        return "redirect:/patients";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "patient/form";
    }

    @PostMapping("/{id}")
    public String updatePatient(@PathVariable String id, @ModelAttribute Patient patient) {
        patientService.update(id, patient);
        return "redirect:/patients";
    }

    @PostMapping("/{id}/delete")
    public String deletePatient(@PathVariable String id) {
        patientService.delete(id);
        return "redirect:/patients";
    }
}
