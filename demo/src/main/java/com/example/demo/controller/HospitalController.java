package com.example.demo.controller;

import com.example.demo.model.Hospital;
import com.example.demo.service.HospitalService;
import jakarta.validation.Valid; // Import pentru validare (@Valid)
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Import pentru rezultate validare
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hospitals") // Ruta principală la PLURAL
public class HospitalController {

    private final HospitalService hospitalService;

    // 1. Listare
    @GetMapping
    public String list(Model model) {
        model.addAttribute("hospitals", hospitalService.getAll());
        return "hospital/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/form";
    }

    // 3. Salvare cu VALIDARE
    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("hospital") Hospital hospital, // Validăm datele
            BindingResult result,                                 // Prindem erorile
            Model model) {

        // A. Validare Format (ex: câmpuri goale)
        if (result.hasErrors()) {
            return "hospital/form"; // Rămânem în formular pentru a afișa erorile
        }

        try {
            // B. Validare Business (ex: Unicitate Nume + Oraș)
            hospitalService.save(hospital);
        } catch (RuntimeException e) {
            // Dacă Service-ul aruncă eroare (ex: "Există deja"), o punem pe câmpul 'name'
            result.rejectValue("name", "error.hospital", e.getMessage());
            return "hospital/form";
        }

        return "redirect:/hospitals";
    }

    // 4. Formular Editare
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/form";
    }

    // 5. Ștergere cu Protecție
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            hospitalService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Hospital deleted successfully!");
        } catch (Exception e) {
            // Prindem eroarea de Foreign Key (dacă spitalul are departamente/camere)
            redirectAttributes.addFlashAttribute("error", "Cannot delete hospital! It has Departments or Rooms assigned to it.");
        }
        return "redirect:/hospitals";
    }

    // 6. Detalii
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/details";
    }
}