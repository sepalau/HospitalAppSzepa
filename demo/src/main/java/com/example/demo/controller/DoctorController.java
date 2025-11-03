package com.example.demo.controller;

import com.example.demo.model.Doctor;
import com.example.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public String create(@RequestBody Doctor doctor) {
        doctorService.create(doctor);
        return "Doctor created successfully.";
    }

    @GetMapping
    public List<Doctor> readAll() {
        return doctorService.readAll();
    }

    @GetMapping("/{id}")
    public Doctor findById(@PathVariable String id) {
        return doctorService.findById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Doctor updatedDoctor) {
        doctorService.update(id, updatedDoctor);
        return "Doctor updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        doctorService.delete(id);
        return "Doctor deleted successfully.";
    }
}