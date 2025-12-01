package com.example.demo.controller;

import com.example.demo.model.Hospital;
import com.example.demo.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hospitals") // Plural definit aici
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("hospitals", hospitalService.getAll());
        return "hospital/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Hospital hospital) {
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/form";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/details";
    }

    // Am adăugat protecție la ștergere
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            hospitalService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Hospital deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete hospital! It has associated Departments or Rooms.");
        }
        return "redirect:/hospitals";
    }
}