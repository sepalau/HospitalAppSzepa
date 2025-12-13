package com.example.demo.controller;

import com.example.demo.model.Doctor;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DoctorService;
import jakarta.validation.Valid; // Import pentru validare
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Import pentru rezultate validare
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/doctors") // Rute la PLURAL
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    // 1. Listare
    @GetMapping
    public String list(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "doctor/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        populateModel(model); // Încărcăm lista de departamente
        return "doctor/form";
    }

    // 3. Salvare cu VALIDARE
    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("doctor") Doctor doctor, // Validăm datele
            BindingResult result,                           // Prindem erorile
            Model model
    ) {
        // A. Validare Format (ex: câmpuri goale)
        if (result.hasErrors()) {
            populateModel(model); // Reîncărcăm listele
            return "doctor/form"; // Rămânem în formular
        }

        try {
            // B. Validare Business (ex: Unicitate Licență)
            doctorService.save(doctor);
        } catch (RuntimeException e) {
            // Dacă Service-ul aruncă eroare (Licență duplicată), o punem pe câmpul 'licenseNumber'
            result.rejectValue("licenseNumber", "error.doctor", e.getMessage());

            populateModel(model);
            return "doctor/form";
        }

        return "redirect:/doctors";
    }

    // 4. Formular Editare
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getById(id));
        populateModel(model);
        return "doctor/form";
    }

    // 5. Ștergere cu Protecție
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            doctorService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Doctor deleted successfully!");
        } catch (Exception e) {
            // Prindem eroarea de Foreign Key (dacă are programări)
            redirectAttributes.addFlashAttribute("error", "Cannot delete doctor! They have assigned Appointments.");
        }
        return "redirect:/doctors";
    }

    // 6. Detalii
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getById(id));
        return "doctor/details";
    }

    // Metodă ajutătoare pentru a popula dropdown-ul de departamente
    private void populateModel(Model model) {
        model.addAttribute("departments", departmentService.getAll());
    }
}