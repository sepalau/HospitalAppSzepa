package com.example.demo.controller;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.service.MedicalStaffAppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicalstaffapp")
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService service;

    public MedicalStaffAppointmentController(MedicalStaffAppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("items", service.readAll());
        return "medicalstaffapp/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("medicalStaffAppointment", new MedicalStaffAppointment());
        return "medicalstaffapp/form";
    }

    @PostMapping
    public String create(@ModelAttribute MedicalStaffAppointment msa) {
        service.create(msa);
        return "redirect:/medicalstaffapp";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("medicalStaffAppointment", service.findById(id));
        return "medicalstaffapp/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id,
                         @ModelAttribute MedicalStaffAppointment msa) {
        service.update(id, msa);
        return "redirect:/medicalstaffapp";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/medicalstaffapp";
    }
}
