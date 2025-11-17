package com.example.demo.controller;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.service.MedicalStaffAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicalstaffapp")
public class MedicalStaffAppointmentController {

    @Autowired
    private MedicalStaffAppointmentService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("medicalStaffAppointments", service.readAll());
        return "medicalstaffapp/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("medicalStaffAppointment", new MedicalStaffAppointment());
        return "medicalstaffapp/form";
    }

    @PostMapping
    public String create(@ModelAttribute MedicalStaffAppointment msa) {
        service.create(msa);
        return "redirect:/medicalstaffapp";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/medicalstaffapp";
    }
}
