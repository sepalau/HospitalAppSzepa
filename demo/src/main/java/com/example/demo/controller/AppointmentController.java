package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.readAll());
        return "appointment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "appointment/form";
    }

    @PostMapping
    public String createAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.create(appointment);
        return "redirect:/appointments";
    }

    @PostMapping("/{id}/delete")
    public String deleteAppointment(@PathVariable String id) {
        appointmentService.delete(id);
        return "redirect:/appointments";
    }
}
