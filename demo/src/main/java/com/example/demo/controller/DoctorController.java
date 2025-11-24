package com.example.demo.controller;

import com.example.demo.model.Doctor;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "doctor/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentService.getAll());
        return "doctor/form";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("doctor") Doctor doctor,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAll());
            return "doctor/form";
        }

        doctorService.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getById(id));
        model.addAttribute("departments", departmentService.getAll());
        return "doctor/form";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("doctor") Doctor doctor,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAll());
            return "doctor/form";
        }

        doctor.setId(id);
        doctorService.save(doctor);

        return "redirect:/doctors";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        doctorService.delete(id);
        return "redirect:/doctors";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getById(id));
        return "doctor/details";
    }
}
