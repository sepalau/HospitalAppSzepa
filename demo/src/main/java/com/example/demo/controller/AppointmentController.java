package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.PatientService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.NurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DepartmentService departmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final NurseService nurseService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", appointmentService.getAll());
        return "appointment/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("patients", patientService.getAll());
        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("nurses", nurseService.getAll());
        return "appointment/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Appointment appointment) {
        appointmentService.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        return "appointment/details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return "redirect:/appointments";
    }
}
