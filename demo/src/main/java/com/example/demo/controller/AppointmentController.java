package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DepartmentService departmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final NurseService nurseService;

    public AppointmentController(AppointmentService appointmentService,
                                 DepartmentService departmentService,
                                 PatientService patientService,
                                 DoctorService doctorService,
                                 NurseService nurseService) {

        this.appointmentService = appointmentService;
        this.departmentService = departmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.nurseService = nurseService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", appointmentService.findAll());
        return "appointment/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        List<MedicalStaff> staffList = new ArrayList<>();

        // Add doctors
        for (Doctor d : doctorService.findAll()) {
            staffList.add(d);
        }

        // Add nurses
        for (Nurse n : nurseService.findAll()) {
            staffList.add(n);
        }

        model.addAttribute("appointment", new Appointment());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("staffList", staffList);

        return "appointment/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Appointment appointment) {
        appointmentService.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.findById(id));
        return "appointment/details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return "redirect:/appointments";
    }
}
