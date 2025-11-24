package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DepartmentService departmentService;
    private final PatientService patientService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", appointmentService.getAll());
        return "appointment/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("patients", patientService.getAll());
        return "appointment/form";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("appointment") Appointment appointment,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAll());
            model.addAttribute("patients", patientService.getAll());
            return "appointment/form";
        }

        appointmentService.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("patients", patientService.getAll());
        return "appointment/form";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("appointment") Appointment appointment,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAll());
            model.addAttribute("patients", patientService.getAll());
            return "appointment/form";
        }

        appointment.setId(id);
        appointmentService.save(appointment);
        return "redirect:/appointments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return "redirect:/appointments";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        return "appointment/details";
    }
}
