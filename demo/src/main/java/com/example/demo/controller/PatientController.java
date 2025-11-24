package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("patients", service.readAll());
        return "patient/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("patient") Patient patient,
                         BindingResult result) {

        if (result.hasErrors())
            return "patient/form";

        service.create(patient);
        return "redirect:/patients";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("patient", service.findById(id));
        return "patient/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id,
                         @Valid @ModelAttribute("patient") Patient patient,
                         BindingResult result) {

        if (result.hasErrors())
            return "patient/form";

        service.update(id, patient);
        return "redirect:/patients";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/patients";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("patient", service.findById(id));
        return "patient/details";
    }
}