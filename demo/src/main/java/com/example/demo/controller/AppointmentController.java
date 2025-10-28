package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository = new AppointmentRepository();

    @PostMapping
    public String create(@RequestBody Appointment appointment) {
        appointmentRepository.create(appointment);
        return "Appointment created successfully.";
    }

    @GetMapping
    public List<Appointment> readAll() {
        return appointmentRepository.readAll();
    }

    @GetMapping("/{id}")
    public Appointment findById(@PathVariable String id) {
        return appointmentRepository.findById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Appointment updatedAppointment) {
        appointmentRepository.update(id, updatedAppointment);
        return "Appointment updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        appointmentRepository.delete(id);
        return "Appointment deleted successfully.";
    }
}
