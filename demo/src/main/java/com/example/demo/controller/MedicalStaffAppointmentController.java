package com.example.demo.controller;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.service.MedicalStaffAppointmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicalstaffapp")
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService service;

    public MedicalStaffAppointmentController(MedicalStaffAppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", service.readAll());
        return "medicalstaffapp/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("medicalStaffAppointment", new MedicalStaffAppointment());
        return "medicalstaffapp/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("medicalStaffAppointment") MedicalStaffAppointment msa,
                         BindingResult result) {

        if (result.hasErrors())
            return "medicalstaffapp/form";

        service.create(msa);
        return "redirect:/medicalstaffapp";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("medicalStaffAppointment", service.findById(id));
        return "medicalstaffapp/form";
    }

    @PostMapping("/{id}/update")
    public String update (@PathVariable String id,
                          @Valid @ModelAttribute("medicalStaffAppointment") MedicalStaffAppointment msa,
                          BindingResult result) {

        if (result.hasErrors())
            return "medicalstaffapp/form";

        service.update(id, msa);
        return "redirect:/medicalstaffapp";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/medicalstaffapp";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("medicalStaffAppointment", service.findById(id));
        return "medicalstaffapp/details";
    }
}
