package com.example.demo.controller;

import com.example.demo.model.MedicalStaff;
import com.example.demo.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalstaff")
public class MedicalStaffController {

    @Autowired
    private MedicalStaffService medicalStaffService;

    @PostMapping
    public String create(@RequestBody MedicalStaff medicalStaff) {
        medicalStaffService.create(medicalStaff);
        return "Medical staff created successfully.";
    }

    @GetMapping
    public List<MedicalStaff> readAll() {
        return medicalStaffService.readAll();
    }

    @GetMapping("/{id}")
    public MedicalStaff findById(@PathVariable String id) {
        return medicalStaffService.findById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody MedicalStaff updatedStaff) {
        medicalStaffService.update(id, updatedStaff);
        return "Medical staff updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        medicalStaffService.delete(id);
        return "Medical staff deleted successfully.";
    }
}