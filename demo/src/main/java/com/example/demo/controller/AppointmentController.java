package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", service.readAll());
        return "appointment/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "appointment/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("appointment") Appointment appointment,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "appointment/form";
        }

        service.create(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("appointment", service.findById(id));
        return "appointment/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id,
                         @Valid @ModelAttribute("appointment") Appointment appointment,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "appointment/form";
        }

        service.update(id, appointment);
        return "redirect:/appointments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/appointments";
    }
}
