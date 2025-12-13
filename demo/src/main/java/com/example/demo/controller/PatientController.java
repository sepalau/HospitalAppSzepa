package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // 1. Listare cu CĂUTARE și SORTARE
    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) String name,
                       @RequestParam(defaultValue = "name") String sortBy, // Sortare default după Nume
                       @RequestParam(defaultValue = "asc") String sortDir) {

        // Obținem lista filtrată
        List<Patient> patients = patientService.searchPatients(name, sortBy, sortDir);
        model.addAttribute("patients", patients);

        // Trimitem parametrii înapoi la View (pentru a păstra textul în input și direcția sortării)
        model.addAttribute("name", name);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        // Helper pentru inversarea sortării la click pe coloană
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patient/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("patient") Patient patient,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patient/form";
        }
        patientService.save(patient);
        redirectAttributes.addFlashAttribute("success", "Pacient salvat cu succes!");
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getById(id));
        return "patient/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            patientService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Pacient șters cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Nu se poate șterge pacientul (are programări active).");
        }
        return "redirect:/patients";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Patient patient = patientService.getById(id);
        model.addAttribute("patient", patient);
        return "patient/details";
    }
}