package com.example.demo.controller;

import com.example.demo.model.MedicalStaff;
import com.example.demo.service.MedicalStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicalstaff")
public class MedicalStaffController {

    private final MedicalStaffService staffService;

    public MedicalStaffController(MedicalStaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("medicalStaff", staffService.readAll());
        return "medicalstaff/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("staff", new MedicalStaff() {});
        return "medicalstaff/form";
    }

    @PostMapping
    public String createStaff(@ModelAttribute MedicalStaff staff) {
        staffService.create(staff);
        return "redirect:/medicalstaff";
    }

    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable String id) {
        staffService.delete(id);
        return "redirect:/medicalstaff";
    }
}