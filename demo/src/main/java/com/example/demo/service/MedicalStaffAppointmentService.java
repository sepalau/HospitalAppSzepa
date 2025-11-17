package com.example.demo.service;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.repository.MedicalStaffAppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffAppointmentService {

    private final MedicalStaffAppointmentRepository repository;

    public MedicalStaffAppointmentService(MedicalStaffAppointmentRepository repository) {
        this.repository = repository;
    }

    public void create(MedicalStaffAppointment appointment) {
        repository.create(appointment);
    }

    public List<MedicalStaffAppointment> readAll() {
        return repository.findAll();
    }

    public MedicalStaffAppointment findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
