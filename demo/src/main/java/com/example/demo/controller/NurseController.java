package com.example.demo.controller;

import com.example.demo.enums.QualificationLevel;
import com.example.demo.model.Nurse;
import com.example.demo.service.NurseService;
import com.example.demo.service.DepartmentService;
import jakarta.validation.Valid; // Import pentru validare
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Import pentru rezultatele validării
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nurses") // Rute la PLURAL
public class NurseController {

    private final NurseService nurseService;
    private final DepartmentService departmentService;

    // 1. Listare
    @GetMapping
    public String list(Model model) {
        model.addAttribute("nurses", nurseService.getAll());
        return "nurse/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        populateModel(model); // Încărcăm dropdown-urile
        return "nurse/form";
    }

    // 3. Salvare cu VALIDARE
    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("nurse") Nurse nurse, // Activează validările din Nurse.java
            BindingResult result,                        // Aici primim erorile
            Model model) {

        // Validare de format (ex: nume gol, departament neselectat)
        if (result.hasErrors()) {
            populateModel(model); // Reîncărcăm listele pentru dropdown-uri
            return "nurse/form";  // Rămânem în formular pentru a afișa erorile
        }

        try {
            // Validare Business (Unicitate) - Service-ul aruncă eroare dacă există deja
            nurseService.save(nurse);
        } catch (RuntimeException e) {
            // Dacă Service-ul zice că există deja, punem eroarea pe câmpul 'name'
            result.rejectValue("name", "error.nurse", e.getMessage());

            populateModel(model);
            return "nurse/form";
        }

        return "redirect:/nurses";
    }

    // 4. Formular Editare (Ruta: /nurses/edit/{id})
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("nurse", nurseService.getById(id));
        populateModel(model);
        return "nurse/form";
    }

    // 5. Ștergere cu Protecție (Ruta: /nurses/delete/{id})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            nurseService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Nurse deleted successfully!");
        } catch (Exception e) {
            // Prindem eroarea de Foreign Key (dacă asistenta are asignări)
            redirectAttributes.addFlashAttribute("error", "Cannot delete nurse. They are assigned to existing Appointments.");
        }
        return "redirect:/nurses";
    }

    // 6. Detalii (Ruta: /nurses/details/{id})
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("nurse", nurseService.getById(id));
        return "nurse/details";
    }

    // Metodă ajutătoare pentru a popula dropdown-urile (Departamente, Calificări)
    private void populateModel(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("levels", QualificationLevel.values());
    }
}