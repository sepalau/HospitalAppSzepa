package com.example.demo.controller;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.MedicalStaffAppointmentService;
import com.example.demo.service.NurseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/medicalstaffapp")
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService msaService;
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final NurseService nurseService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("assigned", msaService.getAll());
        return "medicalstaffapp/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("assignment", new MedicalStaffAppointment());
        model.addAttribute("appointments", appointmentService.getAll());
        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("nurses", nurseService.getAll());
        return "medicalstaffapp/form";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("assignment") MedicalStaffAppointment assignment,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("appointments", appointmentService.getAll());
            model.addAttribute("doctors", doctorService.getAll());
            model.addAttribute("nurses", nurseService.getAll());
            return "medicalstaffapp/form";
        }

        msaService.save(assignment);
        return "redirect:/medicalstaffapp";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", msaService.getById(id));
        return "medicalstaffapp/details";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        msaService.delete(id);
        return "redirect:/medicalstaffapp";
    }
}
