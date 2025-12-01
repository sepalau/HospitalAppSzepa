package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.repository.MedicalStaffRepository; // Importam Repository-ul direct
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DepartmentService departmentService;

    // AICI: Folosim direct Repository-ul pentru Medical Staff
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

        // Populăm dropdown-urile
        model.addAttribute("patients", patientService.getAll());
        model.addAttribute("departments", departmentService.getAll());

        // AICI: Luam lista direct din Repository
        model.addAttribute("staffList", medicalStaffRepository.findAll());

        return "appointment/form";
    }

    @PostMapping
    public String save(@ModelAttribute Appointment appointment,
                       @RequestParam(required = false) Long patientId,
                       @RequestParam(required = false) Long departmentId,
                       @RequestParam(required = false) Long staffId) {

        // Trimitem ID-urile la Service pentru salvare
        appointmentService.save(appointment, patientId, departmentId, staffId);

        return "redirect:/appointments";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));

        // Populăm dropdown-urile și la editare
        model.addAttribute("patients", patientService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("staffList", medicalStaffRepository.findAll()); // Direct din Repo

        return "appointment/form";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return "redirect:/appointments";
    }
    // ... alte metode ...

    @GetMapping("/{id}/details")
    public String details(@PathVariable Long id, Model model) {
        // Cautam programarea dupa ID si o trimitem la pagina HTML
        model.addAttribute("appointment", appointmentService.getById(id));
        return "appointment/details";
    }
}
