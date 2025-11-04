package com.example.demo.controller;

import com.example.demo.model.MedicalStaff;
import com.example.demo.service.MedicalStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicalstaff")
public class MedicalStaffController {

    private final MedicalStaffService service;

    public MedicalStaffController(MedicalStaffService service) {
        this.service = service;
    }

    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("medicalStaff", service.readAll());
        return "medicalstaff/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("staff", new MedicalStaff() {});
        return "medicalstaff/form";
    }

    @PostMapping
    public String createStaff(@ModelAttribute MedicalStaff staff) {
        service.create(staff);
        return "redirect:/medicalstaff";
    }

    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable String id) {
        service.delete(id);
        return "redirect:/medicalstaff";
    }
}
