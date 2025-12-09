package com.example.demo.controller;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.repository.MedicalStaffRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.MedicalStaffAppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/medicalstaffapp")
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService service;
    private final AppointmentService appointmentService;
    private final MedicalStaffRepository medicalStaffRepository;

    public MedicalStaffAppointmentController(MedicalStaffAppointmentService service,
                                             AppointmentService appointmentService,
                                             MedicalStaffRepository medicalStaffRepository) {
        this.service = service;
        this.appointmentService = appointmentService;
        this.medicalStaffRepository = medicalStaffRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("links", service.getAll());
        return "medicalstaffapp/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("link", new MedicalStaffAppointment());
        model.addAttribute("appointments", appointmentService.getAll());
        model.addAttribute("staffList", medicalStaffRepository.findAll());
        return "medicalstaffapp/form";
    }

    @PostMapping
    public String save(@ModelAttribute MedicalStaffAppointment link,
                       @RequestParam(required = false) Long appointmentId,
                       @RequestParam(required = false) Long staffId) {

        service.save(link, appointmentId, staffId);
        return "redirect:/medicalstaffapp";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("link", service.getById(id));

        model.addAttribute("appointments", appointmentService.getAll());
        model.addAttribute("staffList", medicalStaffRepository.findAll());

        return "medicalstaffapp/form";
    }

    @GetMapping("/{id}/details")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("link", service.getById(id));
        return "medicalstaffapp/details";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("success", "Assignment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting assignment: " + e.getMessage());
        }
        return "redirect:/medicalstaffapp";
    }
}