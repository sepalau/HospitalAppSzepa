package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public String create(@RequestBody Patient patient) {
        patientService.create(patient);
        return "Patient created successfully.";
    }

    @GetMapping
    public List<Patient> readAll() {
        return patientService.readAll();
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable String id) {
        return patientService.findById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Patient updatedPatient) {
        patientService.update(id, updatedPatient);
        return "Patient updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        patientService.delete(id);
        return "Patient deleted successfully.";
    }
}