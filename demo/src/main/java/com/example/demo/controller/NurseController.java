package com.example.demo.controller;

import com.example.demo.model.Nurse;
import com.example.demo.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nurses")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @GetMapping
    public String listNurses(Model model) {
        model.addAttribute("nurses", nurseService.readAll());
        return "nurse/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "nurse/form";
    }

    @PostMapping
    public String createNurse(@ModelAttribute Nurse nurse) {
        nurseService.create(nurse);
        return "redirect:/nurses";
    }

    @PostMapping("/{id}/delete")
    public String deleteNurse(@PathVariable String id) {
        nurseService.delete(id);
        return "redirect:/nurses";
    }
}