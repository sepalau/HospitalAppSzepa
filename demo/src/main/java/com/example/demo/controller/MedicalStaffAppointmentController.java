package com.example.demo.controller;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.service.MedicalStaffAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicalstaffapp")  // <-- schimbat aici
public class MedicalStaffAppointmentController {

    @Autowired
    private MedicalStaffAppointmentService service;

    @GetMapping
    public String listAppointments(Model model) {
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
        return "redirect:/staff-appointments";  // <-- schimbat aici
    }

    @PostMapping("/{id}/delete")
    public String deleteAppointment(@PathVariable String id) {
        service.delete(id);
        return "redirect:/staff-appointments";  // <-- schimbat aici
    }
}
