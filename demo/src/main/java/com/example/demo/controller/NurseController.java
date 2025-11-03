package com.example.demo.controller;

import com.example.demo.model.Nurse;
import com.example.demo.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurses")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @PostMapping
    public String create(@RequestBody Nurse nurse) {
        nurseService.create(nurse);
        return "Nurse created successfully.";
    }

    @GetMapping
    public List<Nurse> readAll() {
        return nurseService.readAll();
    }

    @GetMapping("/{id}")
    public Nurse findById(@PathVariable String id) {
        return nurseService.findById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Nurse updatedNurse) {
        nurseService.update(id, updatedNurse);
        return "Nurse updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        nurseService.delete(id);
        return "Nurse deleted successfully.";
    }
}