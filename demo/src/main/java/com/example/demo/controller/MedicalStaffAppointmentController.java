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

    // 1. Listare
    @GetMapping
    public String list(Model model) {
        model.addAttribute("links", service.getAll());
        return "medicalstaffapp/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("link", new MedicalStaffAppointment());
        // Populăm dropdown-urile
        model.addAttribute("appointments", appointmentService.getAll());
        model.addAttribute("staffList", medicalStaffRepository.findAll());
        return "medicalstaffapp/form";
    }

    // 3. Salvare (pentru Create și Update)
    @PostMapping
    public String save(@ModelAttribute MedicalStaffAppointment link,
                       @RequestParam(required = false) Long appointmentId,
                       @RequestParam(required = false) Long staffId) {

        service.save(link, appointmentId, staffId);
        return "redirect:/medicalstaffapp";
    }

    // 4. Formular Editare (METODA NOUĂ)
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        // Găsim asignarea existentă
        model.addAttribute("link", service.getById(id));

        // Populăm din nou dropdown-urile pentru a putea schimba valorile
        model.addAttribute("appointments", appointmentService.getAll());
        model.addAttribute("staffList", medicalStaffRepository.findAll());

        return "medicalstaffapp/form";
    }

    // 5. Detalii
    @GetMapping("/{id}/details")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("link", service.getById(id));
        return "medicalstaffapp/details";
    }

    // 6. Ștergere (Cu protecție try-catch)
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