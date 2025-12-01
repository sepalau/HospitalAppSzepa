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

    // 1. Listare (Index)
    @GetMapping
    public String list(Model model) {
        model.addAttribute("assignments", msaService.getAll());
        return "medicalstaffapp/index";
    }

    // 2. Formular Creare Nouă
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("assignment", new MedicalStaffAppointment());
        model.addAttribute("appointments", appointmentService.getAll());
        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("nurses", nurseService.getAll());
        return "medicalstaffapp/form";
    }

    // 3. Metoda de Editare (Aceasta lipsea)
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        // Găsim obiectul existent
        MedicalStaffAppointment assignment = msaService.getById(id);
        model.addAttribute("assignment", assignment);

        // Reîncărcăm listele pentru dropdown-uri
        model.addAttribute("appointments", appointmentService.getAll());
        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("nurses", nurseService.getAll());

        return "medicalstaffapp/form";
    }

    // 4. Salvare (Create sau Update)
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

    // 5. Detalii
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", msaService.getById(id));
        return "medicalstaffapp/details";
    }

    // 6. Ștergere
    // Am schimbat în GetMapping ca să meargă cu link-ul simplu din HTML
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        msaService.delete(id);
        return "redirect:/medicalstaffapp";
    }
}