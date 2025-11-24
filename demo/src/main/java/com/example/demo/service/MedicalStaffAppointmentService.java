package com.example.demo.service;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.repository.MedicalStaffAppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffAppointmentService {

    private final MedicalStaffAppointmentRepository repo;

    public MedicalStaffAppointmentService(MedicalStaffAppointmentRepository repo) {
        this.repo = repo;
    }

    public List<MedicalStaffAppointment> readAll() {
        return repo.findAll();
    }

    public MedicalStaffAppointment findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void create(MedicalStaffAppointment msa) {
        repo.save(msa);
    }

    public void update(String id, MedicalStaffAppointment msa) {
        repo.save(msa);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}