package com.example.demo.controller;

import com.example.demo.dto.MedicalStaffAssignmentForm;
import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.MedicalStaffAppointmentService;
import com.example.demo.service.NurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        // Folosim DTO-ul aici, nu entitatea directă
        model.addAttribute("assignment", new MedicalStaffAssignmentForm());

        // Populăm dropdown-urile
        model.addAttribute("appointments", appointmentService.getAll());
        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("nurses", nurseService.getAll());

        return "medicalstaffapp/form";
    }

    // 3. Salvare (Create sau Update)
    @PostMapping("/save")
    public String save(@ModelAttribute("assignment") MedicalStaffAssignmentForm form) {
        // Apelăm metoda specială din service care știe să lucreze cu DTO
        msaService.saveForm(form);
        return "redirect:/medicalstaffapp";
    }

    // 4. Metoda de Editare
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        // Găsim entitatea existentă
        MedicalStaffAppointment entity = msaService.getById(id);

        // Convertim entitatea în DTO pentru a popula formularul
        MedicalStaffAssignmentForm form = msaService.toForm(entity);

        model.addAttribute("assignment", form);

        // Reîncărcăm listele pentru dropdown-uri
        model.addAttribute("appointments", appointmentService.getAll());
        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("nurses", nurseService.getAll());

        return "medicalstaffapp/form";
    }

    // 5. Detalii
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", msaService.getById(id));
        return "medicalstaffapp/details";
    }

    // 6. Ștergere
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            msaService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Assignment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting assignment.");
        }
        return "redirect:/medicalstaffapp";
    }
}