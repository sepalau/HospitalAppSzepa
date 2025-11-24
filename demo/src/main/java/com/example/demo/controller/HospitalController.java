package com.example.demo.controller;

import com.example.demo.model.Hospital;
import com.example.demo.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService service;

    public HospitalController(HospitalService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("hospitals", service.readAll());
        return "hospital/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("hospital") Hospital hospital,
                         BindingResult result) {

        if (result.hasErrors())
            return "hospital/form";

        service.create(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("hospital", service.findById(id));
        return "hospital/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id,
                         @Valid @ModelAttribute("hospital") Hospital hospital,
                         BindingResult result) {

        if (result.hasErrors())
            return "hospital/form";

        service.update(id, hospital);
        return "redirect:/hospitals";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/hospitals";
    }

    // DETAILS PAGE
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("hospital", service.findById(id));
        return "hospital/details";
    }
}
