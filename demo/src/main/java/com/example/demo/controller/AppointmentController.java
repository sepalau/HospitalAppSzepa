package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.repository.MedicalStaffRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DepartmentService departmentService;
    private final MedicalStaffRepository medicalStaffRepository;

    public AppointmentController(AppointmentService appointmentService,
                                 PatientService patientService,
                                 DepartmentService departmentService,
                                 MedicalStaffRepository medicalStaffRepository) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.departmentService = departmentService;
        this.medicalStaffRepository = medicalStaffRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", appointmentService.getAll());
        return "appointment/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("staffList", medicalStaffRepository.findAll());
        return "appointment/form";
    }

    @PostMapping
    public String save(@ModelAttribute Appointment appointment,
                       @RequestParam(required = false) Long patientId,
                       @RequestParam(required = false) Long departmentId,
                       @RequestParam(required = false) Long staffId) {
        appointmentService.save(appointment, patientId, departmentId, staffId);
        return "redirect:/appointments";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        model.addAttribute("patients", patientService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("staffList", medicalStaffRepository.findAll());
        return "appointment/form";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            appointmentService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Appointment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete appointment! It is linked to Medical Staff Assignments. Delete the assignment first.");
        }
        return "redirect:/appointments";
    }

    @GetMapping("/{id}/details")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        return "appointment/details";
    }
}