package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import jakarta.validation.Valid; // Import necesar pentru validare
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Import necesar pentru rezultatul validării
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // 1. Listare Pacienți
    @GetMapping
    public String list(Model model) {
        model.addAttribute("patients", patientService.getAll());
        return "patient/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }

    // 3. Salvare cu Validare (FILTRAREA DATELOR DE INTRARE)
    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("patient") Patient patient, // @Valid declanșează regulile din Patient.java
            BindingResult result,                              // Aici se stochează erorile de validare
            Model model
    ) {
        // Pasul de Filtrare: Verificăm dacă datele sunt corecte
        if (result.hasErrors()) {
            // Dacă există erori (ex: nume gol, adresă prea scurtă):
            // 1. NU apelăm service.save()
            // 2. Rămânem pe pagina formularului ("patient/form")
            // 3. Thymeleaf va afișa automat erorile lângă câmpuri
            return "patient/form";
        }

        // Dacă datele sunt valide, salvăm în baza de date
        patientService.save(patient);
        return "redirect:/patients";
    }

    // 4. Formular Editare
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