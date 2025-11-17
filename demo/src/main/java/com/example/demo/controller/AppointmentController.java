package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public String listAppointments(Model model) {
        model.addAttribute("appointments", service.readAll());
        return "appointment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "appointment/form";
    }

    @PostMapping
    public String create(@ModelAttribute Appointment appointment) {
        service.create(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("appointment", service.findById(id));
        return "appointment/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute Appointment appointment) {
        service.update(id, appointment);
        return "redirect:/appointments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/appointments";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable String id, Model model) {
        model.addAttribute("appointment", service.findById(id));
        return "appointment/details";
    }
}