package com.example.demo.controller;

import com.example.demo.dto.AppointmentForm;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.MedicalStaffRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/appointments")
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
        populateModel(model);
        return "appointment/form";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("appointment") AppointmentForm form,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            populateModel(model);
            return "appointment/form";
        }

        appointmentService.saveForm(form);
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Appointment a = appointmentService.getById(id);
        AppointmentForm form = appointmentService.toForm(a);

        model.addAttribute("appointment", form);
        populateModel(model);

        return "appointment/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            appointmentService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Appointment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete appointment. It might be linked to other records.");
        }
        return "redirect:/appointments";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        return "appointment/details";
    }

    private void populateModel(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("medicalStaffList", medicalStaffRepository.findAll());
    }
}