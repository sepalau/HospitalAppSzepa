package com.example.demo.controller;

import com.example.demo.model.Hospital;
import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.repository.HospitalRepository;
import com.example.demo.service.MedicalStaffAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalRepository hospitalRepository = new HospitalRepository();

    @PostMapping
    public String create(@RequestBody Hospital hospital) {
        hospitalRepository.create(hospital);
        return "Hospital created successfully.";
    }

    @GetMapping
    public List<Hospital> readAll() {
        return hospitalRepository.readAll();
    }

    @GetMapping("/{id}")
    public Hospital findById(@PathVariable String id) {
        return hospitalRepository.findById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Hospital updatedHospital) {
        hospitalRepository.update(id, updatedHospital);
        return "Hospital updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        hospitalRepository.delete(id);
        return "Hospital deleted successfully.";
    }
}

