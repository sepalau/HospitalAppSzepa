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
        return repo.findById(id);
    }

    public void create(MedicalStaffAppointment msa) {
        repo.create(msa);
    }

    public void update(String id, MedicalStaffAppointment msa) {
        repo.update(id, msa);
    }

    public void delete(String id) {
        repo.delete(id);
    }
}
