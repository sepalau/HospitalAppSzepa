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

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // 1. Listare toți pacienții
    @GetMapping
    public String list(Model model) {
        model.addAttribute("patients", patientService.getAll());
        return "patient/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }

    // 3. Salvare (Create & Update)
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

    // 4. Formular Editare
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getById(id));
        return "patient/form";
    }

    // 5. Ștergere
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            patientService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Pacient șters cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Nu se poate șterge pacientul deoarece are programări active.");
        }
        return "redirect:/patients";
    }

    // 6. AFISARE DETALII + ISTORIC PROGRAMĂRI (NOU)
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        // Această metodă va încărca pacientul și automat lista de appointments (datorită @OneToMany)
        Patient patient = patientService.getById(id);
        model.addAttribute("patient", patient);
        return "patient/details";
    }
}