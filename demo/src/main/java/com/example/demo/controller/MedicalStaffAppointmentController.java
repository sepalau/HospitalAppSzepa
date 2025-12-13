package com.example.demo.controller;

import com.example.demo.dto.MedicalStaffAssignmentForm;
import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.MedicalStaffRepository;
import com.example.demo.service.MedicalStaffAppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/assignments") // URL-ul din browser
@RequiredArgsConstructor
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService service;
    private final AppointmentRepository appointmentRepository;
    private final MedicalStaffRepository medicalStaffRepository;

    // 1. LISTARE + FILTRARE + SORTARE
    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) String staffName,
                       @RequestParam(required = false) Long appointmentId,
                       @RequestParam(defaultValue = "id") String sortBy,
                       @RequestParam(defaultValue = "asc") String sortDir) {

        List<MedicalStaffAppointment> assignments = service.search(staffName, appointmentId, sortBy, sortDir);
        model.addAttribute("assignments", assignments);

        // Parametrii pentru UI (Sticky inputs)
        model.addAttribute("staffName", staffName);
        model.addAttribute("appointmentId", appointmentId);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        // CORECTAT: Acum returnează din folderul 'assignments'
        return "assignments/index";
    }

    // 2. FORMULAR ADĂUGARE
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("form", new MedicalStaffAssignmentForm());
        populateDropdowns(model);
        // CORECTAT
        return "assignments/form";
    }

    // 3. EDITARE
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        MedicalStaffAppointment assignment = service.getById(id);
        MedicalStaffAssignmentForm form = service.toForm(assignment);

        model.addAttribute("form", form);
        populateDropdowns(model);

        // CORECTAT
        return "assignments/form";
    }

    // 4. SALVARE
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("form") MedicalStaffAssignmentForm form,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            populateDropdowns(model);
            return "assignments/form"; // CORECTAT
        }

        try {
            service.saveForm(form);
            redirectAttributes.addFlashAttribute("success", "Asignare salvată cu succes!");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            populateDropdowns(model);
            return "assignments/form"; // CORECTAT
        }

        return "redirect:/assignments";
    }

    // 5. ȘTERGERE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("success", "Legătura a fost ștearsă!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Eroare la ștergere.");
        }
        return "redirect:/assignments";
    }

    private void populateDropdowns(Model model) {
        model.addAttribute("allStaff", medicalStaffRepository.findAll());
        model.addAttribute("allAppointments", appointmentRepository.findAll());
    }
}