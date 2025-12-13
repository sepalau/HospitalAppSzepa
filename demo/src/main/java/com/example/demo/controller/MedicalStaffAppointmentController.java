package com.example.demo.controller;

import com.example.demo.dto.MedicalStaffAssignmentForm;
import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.MedicalStaffAppointmentService;
import com.example.demo.repository.MedicalStaffRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/medicalstaffapp")
@RequiredArgsConstructor
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService msaService;
    private final AppointmentService appointmentService;
    private final MedicalStaffRepository medicalStaffRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("links", msaService.getAll());
        return "medicalstaffapp/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("assignment", new MedicalStaffAssignmentForm());
        populateModel(model);
        return "medicalstaffapp/form";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("assignment") MedicalStaffAssignmentForm form,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            populateModel(model);
            return "medicalstaffapp/form";
        }

        try {
            msaService.saveForm(form);
        } catch (RuntimeException e) {
            result.reject("businessError", e.getMessage());
            populateModel(model);
            return "medicalstaffapp/form";
        }

        return "redirect:/medicalstaffapp";
    }

    // --- RUTE CORECTATE (Actiune inainte de ID) ---

    @GetMapping("/edit/{id}") // Era /{id}/edit
    public String editForm(@PathVariable Long id, Model model) {
        MedicalStaffAppointment entity = msaService.getById(id);
        MedicalStaffAssignmentForm form = msaService.toForm(entity);

        model.addAttribute("assignment", form);
        populateModel(model);

        return "medicalstaffapp/form";
    }

    @GetMapping("/details/{id}") // Era /{id}/details
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("link", msaService.getById(id));
        return "medicalstaffapp/details";
    }

    @GetMapping("/delete/{id}") // Era /{id}/delete
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            msaService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Assignment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting assignment.");
        }
        return "redirect:/medicalstaffapp";
    }

    private void populateModel(Model model) {
        model.addAttribute("appointments", appointmentService.getAll());
        model.addAttribute("staffList", medicalStaffRepository.findAll());
    }
}