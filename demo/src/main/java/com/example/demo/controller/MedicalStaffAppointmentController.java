package com.example.demo.controller;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.service.MedicalStaffAppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicalstaffapp")
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService service;

    public MedicalStaffAppointmentController(MedicalStaffAppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllAppointments(Model model) {
        model.addAttribute("appointments", service.readAll());
        return "medicalstaffapp/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("appointment", new MedicalStaffAppointment());
        return "medicalstaffapp/form";
    }

    @PostMapping
    public String createAppointment(@ModelAttribute MedicalStaffAppointment appointment) {
        service.create(appointment);
        return "redirect:/medicalstaffapp";
    }

    @PostMapping("/{id}/delete")
    public String deleteAppointment(@PathVariable String id) {
        service.delete(id);
        return "redirect:/medicalstaffapp";
    }
}
