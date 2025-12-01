package com.example.demo.controller;

import com.example.demo.dto.AppointmentForm;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.MedicalStaffRepository; // Import nou
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final MedicalStaffRepository medicalStaffRepository; // Injectare nouă

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
        // Trimitem lista de medici către HTML
        model.addAttribute("medicalStaffList", medicalStaffRepository.findAll());
        return "appointment/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("appointment") AppointmentForm form) {
        appointmentService.saveForm(form);
        return "redirect:/appointment";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Appointment a = appointmentService.getById(id);
        AppointmentForm form = appointmentService.toForm(a);

        model.addAttribute("appointment", form);
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        // Trimitem lista de medici și la editare
        model.addAttribute("medicalStaffList", medicalStaffRepository.findAll());

        return "appointment/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return "redirect:/appointment";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        return "appointment/details";
    }
}