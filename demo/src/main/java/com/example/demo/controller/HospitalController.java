package com.example.demo.controller;

import com.example.demo.model.Hospital;
import com.example.demo.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public String listHospitals(Model model) {
        model.addAttribute("hospitals", hospitalService.readAll());
        return "hospital/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/form";
    }

    @PostMapping
    public String createHospital(@ModelAttribute Hospital hospital) {
        hospitalService.create(hospital);
        return "redirect:/hospitals";
    }

    @PostMapping("/{id}/delete")
    public String deleteHospital(@PathVariable String id) {
        hospitalService.delete(id);
        return "redirect:/hospitals";
    }
}
