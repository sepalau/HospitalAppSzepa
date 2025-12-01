package com.example.demo.controller;

import com.example.demo.dto.AppointmentForm;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.MedicalStaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments") // <-- Aici este definit PLURALUL
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final MedicalStaffRepository medicalStaffRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", appointmentService.getAll());
        return "appointment/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("appointment", new AppointmentForm());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("medicalStaffList", medicalStaffRepository.findAll());
        return "appointment/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("appointment") AppointmentForm form) {
        appointmentService.saveForm(form);
        // CORECTAT: redirect la plural
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Appointment a = appointmentService.getById(id);
        AppointmentForm form = appointmentService.toForm(a);

        model.addAttribute("appointment", form);
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("medicalStaffList", medicalStaffRepository.findAll());

        return "appointment/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentService.delete(id);
        // CORECTAT: redirect la plural
        return "redirect:/appointments";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        return "appointment/details";
    }
}