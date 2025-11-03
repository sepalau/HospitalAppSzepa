package com.example.demo.controller;

import com.example.demo.model.Hospital;
import com.example.demo.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // ======= REST Endpoints =======

    @PostMapping
    @ResponseBody
    public String create(@RequestBody Hospital hospital) {
        hospitalService.create(hospital);
        return "Hospital created successfully.";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Hospital> readAll() {
        return hospitalService.readAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Hospital findById(@PathVariable String id) {
        return hospitalService.findById(id);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public String update(@PathVariable String id, @RequestBody Hospital updatedHospital) {
        hospitalService.update(id, updatedHospital);
        return "Hospital updated successfully.";
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public String delete(@PathVariable String id) {
        hospitalService.delete(id);
        return "Hospital deleted successfully.";
    }

    // ======= Thymeleaf View Endpoint =======

    @GetMapping("/view")
    public String viewHospitals(Model model) {
        List<Hospital> hospitals = hospitalService.readAll();
        model.addAttribute("hospitals", hospitals);
        return "hospitals"; // randează template-ul hospitals.html
    }
}
