package com.example.demo.controller;

import com.example.demo.enums.QualificationLevel;
import com.example.demo.model.Nurse;
import com.example.demo.service.NurseService;
import com.example.demo.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nurses") // Plural
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
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("levels", QualificationLevel.values());
        return "nurse/form";
    }

    // 3. Salvare
    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("nurse") Nurse nurse,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAll());
            model.addAttribute("levels", QualificationLevel.values());
            return "nurse/form";
        }

        nurseService.save(nurse);
        return "redirect:/nurses";
    }

    // 4. Formular Editare (Standardizat la /edit/{id})
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("nurse", nurseService.getById(id));
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("levels", QualificationLevel.values());
        return "nurse/form";
    }

    // 5. Ștergere (Standardizat la /delete/{id} cu GET și protecție erori)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            nurseService.delete(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete nurse. Check assignments.");
        }
        return "redirect:/nurses";
    }

    // 6. Detalii (Standardizat la /details/{id})
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("nurse", nurseService.getById(id));
        return "nurse/details";
    }
}