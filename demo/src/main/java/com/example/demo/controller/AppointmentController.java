package com.example.demo.controller;

import com.example.demo.model.AppointmentForm;
import com.example.demo.model.*;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final NurseService nurseService;

    // LIST --------------------------------------------------------
    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", appointmentService.getAll());
        return "appointment/index";
    }

    // ADD ---------------------------------------------------------
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("appointmentForm", new AppointmentForm());
        loadCombos(model);
        return "appointment/form";
    }

    // EDIT --------------------------------------------------------
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("appointmentForm", appointmentService.getFormById(id));
        loadCombos(model);
        return "appointment/form";
    }

    // SAVE --------------------------------------------------------
    @PostMapping("/save")
    public String save(@ModelAttribute AppointmentForm form) {
        appointmentService.saveForm(form);
        return "redirect:/appointment";
    }

    // DETAILS -----------------------------------------------------
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        return "appointment/details";
    }

    // DELETE ------------------------------------------------------
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return "redirect:/appointment";
    }


    // Load dropdown values
    private void loadCombos(Model model) {
        model.addAttribute("patients", patientService.getAll());
        model.addAttribute("departments", departmentService.getAll());

        List<MedicalStaff> staff = new ArrayList<>();
        staff.addAll(doctorService.getAll());
        staff.addAll(nurseService.getAll());
        model.addAttribute("medicalStaffList", staff);
    }
}
