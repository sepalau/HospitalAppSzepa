package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("departments", service.readAll());
        return "department/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("department") Department d,
                         BindingResult result) {

        if (result.hasErrors())
            return "department/form";

        service.create(d);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("department", service.findById(id));
        return "department/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id,
                         @Valid @ModelAttribute("department") Department d,
                         BindingResult result) {

        if (result.hasErrors())
            return "department/form";

        service.update(id, d);
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/departments";
    }
}
