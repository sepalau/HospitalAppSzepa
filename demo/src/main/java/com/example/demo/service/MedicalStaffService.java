package com.example.demo.service;

import com.example.demo.model.MedicalStaff;
import com.example.demo.repository.MedicalStaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffService {

    private final MedicalStaffRepository repository;

    public MedicalStaffService(MedicalStaffRepository repository) {
        this.repository = repository;
    }

    public void create(MedicalStaff staff) {
        repository.create(staff);
    }

    public List<MedicalStaff> readAll() {
        return repository.readAll();
    }

    public MedicalStaff findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
