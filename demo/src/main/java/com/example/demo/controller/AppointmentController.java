package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.model.MedicalStaff;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.NurseService;
import com.example.demo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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

    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointment", appointmentService.getAll());
        return "appointment/index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("appointment", new Appointment());

        model.addAttribute("patient", patientService.getAll());
        model.addAttribute("department", departmentService.getAll());

        List<MedicalStaff> staff = new ArrayList<>();
        staff.addAll(doctorService.getAll());
        staff.addAll(nurseService.getAll());
        model.addAttribute("medicalStaffList", staff);

        return "appointment/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("appointment") Appointment appointment,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("patient", patientService.getAll());
            model.addAttribute("department", departmentService.getAll());

            List<MedicalStaff> staff = new ArrayList<>();
            staff.addAll(doctorService.getAll());
            staff.addAll(nurseService.getAll());
            model.addAttribute("medicalStaffList", staff);

            return "appointment/form";
        }

        appointmentService.save(appointment);
        return "redirect:/appointment";
    }
}
