package com.example.demo.service;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.repository.MedicalStaffAppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalStaffAppointmentService {

    private final MedicalStaffAppointmentRepository repo;

    public List<MedicalStaffAppointment> getAll() {
        return repo.findAll();
    }

    public MedicalStaffAppointment getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found: " + id));
    }

    public MedicalStaffAppointment save(MedicalStaffAppointment msa) {
        return repo.save(msa);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
