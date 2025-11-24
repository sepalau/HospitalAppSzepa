package com.example.demo.controller;

import com.example.demo.model.Hospital;
import com.example.demo.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping
    public String listHospitals(Model model) {
        model.addAttribute("hospitals", hospitalService.getAll());
        return "hospital/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/form";
    }

    @PostMapping
    public String createHospital(
            @Valid @ModelAttribute("hospital") Hospital hospital,
            BindingResult result
    ) {
        if (result.hasErrors()) return "hospital/form";

        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/form";
    }

    @PostMapping("/{id}/update")
    public String updateHospital(
            @PathVariable Long id,
            @Valid @ModelAttribute("hospital") Hospital hospital,
            BindingResult result
    ) {
        if (result.hasErrors()) return "hospital/form";

        hospital.setId(id);
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }

    @PostMapping("/{id}/delete")
    public String deleteHospital(@PathVariable Long id) {
        hospitalService.delete(id);
        return "redirect:/hospitals";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/details";
    }
}
