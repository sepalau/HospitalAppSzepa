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
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // IMPORT NECESAR

@Controller
@RequiredArgsConstructor
@RequestMapping("/nurses")
public class NurseController {

    private final NurseService nurseService;
    private final DepartmentService departmentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("nurses", nurseService.getAll());
        return "nurse/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("levels", QualificationLevel.values());
        return "nurse/form";
    }

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

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("nurse", nurseService.getById(id));
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("levels", QualificationLevel.values());
        return "nurse/form";
    }

    // --- AICI ESTE FIX-UL ---
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            nurseService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Nurse deleted successfully!");
        } catch (Exception e) {
            // Prindem eroarea de la baza de date
            redirectAttributes.addFlashAttribute("error", "Cannot delete nurse! They are assigned to Appointments.");
        }
        return "redirect:/nurses";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("nurse", nurseService.getById(id));
        return "nurse/details";
    }
}