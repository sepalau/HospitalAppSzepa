package com.example.demo.controller;

import com.example.demo.model.Nurse;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.NurseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nurses")
public class NurseController {

    private final NurseService nurseService;
    private final DepartmentService departmentService; // NECESAR pentru dropdown-ul de departamente

    @GetMapping
    public String list(Model model) {
        model.addAttribute("nurses", nurseService.getAll());
        return "nurse/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        // Trimitem lista de departamente către view pentru a popula <select>
        model.addAttribute("departments", departmentService.getAll());
        return "nurse/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("nurse") Nurse nurse,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            // Dacă e eroare, trebuie să retrimitem lista de departamente
            model.addAttribute("departments", departmentService.getAll());
            return "nurse/form";
        }
        nurseService.save(nurse);
        return "redirect:/nurses";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("nurse", nurseService.getById(id));
        model.addAttribute("departments", departmentService.getAll());
        return "nurse/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("nurse") Nurse nurse,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAll());
            return "nurse/form";
        }
        nurse.setId(id);
        nurseService.save(nurse);
        return "redirect:/nurses";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        nurseService.delete(id);
        return "redirect:/nurses";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("nurse", nurseService.getById(id));
        return "nurse/details";
    }
}