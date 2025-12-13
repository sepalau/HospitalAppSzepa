package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.HospitalService;
import jakarta.validation.Valid; // Import pentru validare
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Import pentru rezultate validare
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    // 1. Listare
    @GetMapping
    public String index(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "department/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("department", new Department());
        // Trebuie să trimitem lista de spitale pentru dropdown
        model.addAttribute("hospitals", hospitalService.getAll());
        return "department/form";
    }

    // 3. Salvare cu VALIDARE (Format + Business)
    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute Department department,
            BindingResult result,
            Model model) {

        // A. Validare de format (ex: câmpuri goale)
        if (result.hasErrors()) {
            // Reîncărcăm lista de spitale pentru că ne întoarcem în pagină
            model.addAttribute("hospitals", hospitalService.getAll());
            return "department/form";
        }

        try {
            // B. Validare de Business (ex: Unicitate nume)
            // Această metodă din Service poate arunca RuntimeException
            departmentService.save(department);
        } catch (RuntimeException e) {
            // Dacă apare o eroare de business (ex: "Există deja"), o punem pe câmpul 'name'
            result.rejectValue("name", "error.department", e.getMessage());

            // Reîncărcăm lista de spitale
            model.addAttribute("hospitals", hospitalService.getAll());
            return "department/form";
        }

        return "redirect:/departments";
    }

    // 4. Formular Editare
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        model.addAttribute("hospitals", hospitalService.getAll());
        return "department/form";
    }

    // 5. Ștergere cu Protecție
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            departmentService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Department deleted successfully!");
        } catch (Exception e) {
            // Prindem eroarea de bază de date (Foreign Key) dacă departamentul nu e gol
            redirectAttributes.addFlashAttribute("error", "Cannot delete department! It contains Doctors, Nurses or Rooms.");
        }
        return "redirect:/departments";
    }

    // 6. Detalii
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        return "department/details";
    }
}