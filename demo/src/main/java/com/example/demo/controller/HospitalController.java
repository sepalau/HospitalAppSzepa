package com.example.demo.controller;

import com.example.demo.model.Hospital;
import com.example.demo.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    // 1. List with Filtering and Sorting (PDF 5 Requirement)
    @GetMapping
    public String list(
            Model model,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "sortField", defaultValue = "name") String sortField, // Default sort by Name
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir
    ) {
        List<Hospital> list = hospitalService.getAll(keyword, sortField, sortDir);
        model.addAttribute("hospitals", list);

        // Pass parameters back to the view to maintain state
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "hospital/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/form";
    }

    // 2. Save with Validation (PDF 4 Requirement)
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("hospital") Hospital hospital,
                       BindingResult result, Model model) {
        // A. Format Validation (@NotBlank, etc.)
        if (result.hasErrors()) {
            return "hospital/form";
        }

        try {
            // B. Business Validation (Unique Name in City)
            hospitalService.save(hospital);
        } catch (RuntimeException e) {
            // If service throws duplicate error, add it to the 'name' field
            result.rejectValue("name", "error.hospital", e.getMessage());
            return "hospital/form";
        }

        return "redirect:/hospitals";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/form";
    }

    // 3. Delete with Protection (PDF 4 Requirement)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            hospitalService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Hospital deleted successfully!");
        } catch (Exception e) {
            // Catch DB Foreign Key constraint error if hospital has departments/rooms
            redirectAttributes.addFlashAttribute("error", "Cannot delete hospital! It has Departments or Rooms assigned to it.");
        }
        return "redirect:/hospitals";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/details";
    }
}