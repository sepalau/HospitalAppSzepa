package com.example.demo.controller;

import com.example.demo.model.Nurse;
import com.example.demo.service.NurseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nurses")
public class NurseController {

    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("nurses", nurseService.readAll());
        return "nurse/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "nurse/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("nurse") Nurse nurse,
                         BindingResult result) {

        if (result.hasErrors())
            return "nurse/form";

        nurseService.create(nurse);
        return "redirect:/nurses";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("nurse", nurseService.findById(id));
        return "nurse/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id,
                         @Valid @ModelAttribute("nurse") Nurse nurse,
                         BindingResult result) {

        if (result.hasErrors())
            return "nurse/form";

        nurseService.update(id, nurse);
        return "redirect:/nurses";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        nurseService.delete(id);
        return "redirect:/nurses";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("nurse", nurseService.findById(id));
        return "nurse/details";
    }
}