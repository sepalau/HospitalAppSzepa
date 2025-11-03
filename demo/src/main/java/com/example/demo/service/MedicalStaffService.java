package com.example.demo.service;

import com.example.demo.model.MedicalStaff;
import com.example.demo.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffService {

    @Autowired
    private MedicalStaffRepository medicalStaffRepository;

    public void create(MedicalStaff medicalStaff) {
        medicalStaffRepository.create(medicalStaff);
    }

    public List<MedicalStaff> readAll() {
        return medicalStaffRepository.readAll();
    }

    public MedicalStaff findById(String id) {
        return medicalStaffRepository.findById(id);
    }

    public void update(String id, MedicalStaff updatedStaff) {
        medicalStaffRepository.update(id, updatedStaff);
    }

    public void delete(String id) {
        medicalStaffRepository.delete(id);
    }
}