package com.example.demo.controller;

import com.example.demo.dto.AppointmentForm;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.MedicalStaffRepository;
import jakarta.validation.Valid; // Import pentru validare
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Import pentru rezultate validare
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final MedicalStaffRepository medicalStaffRepository;

    // 1. Listare
    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", appointmentService.getAll());
        return "appointment/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("appointment", new AppointmentForm());
        populateModel(model); // Încărcăm listele pentru dropdown-uri
        return "appointment/form";
    }

    // 3. Salvare cu VALIDARE
    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("appointment") AppointmentForm form, // @Valid activează regulile din DTO
            BindingResult result,                                       // Aici primim erorile
            Model model
    ) {
        // Dacă avem erori (ex: data e în trecut, câmpuri goale)
        if (result.hasErrors()) {
            // Reîncărcăm listele (pacienți, medici) pentru că ne întoarcem în pagină
            populateModel(model);
            // Rămânem în formular pentru a arăta greșelile
            return "appointment/form";
        }

        // Dacă totul e OK, salvăm
        appointmentService.saveForm(form);
        return "redirect:/appointments";
    }

    // 4. Formular Editare
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Appointment a = appointmentService.getById(id);
        // Convertim entitatea în DTO pentru formular
        AppointmentForm form = appointmentService.toForm(a);

        model.addAttribute("appointment", form);
        populateModel(model); // Încărcăm listele

        return "appointment/form";
    }

    // 5. Ștergere (cu protecție)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            appointmentService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Appointment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete appointment. It might be linked to other records.");
        }
        return "redirect:/appointments";
    }

    // 6. Detalii
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        return "appointment/details";
    }

    // Metodă ajutătoare pentru a nu repeta codul de încărcare a listelor
    private void populateModel(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("medicalStaffList", medicalStaffRepository.findAll());
    }
}