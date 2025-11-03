package com.example.demo.service;

import com.example.demo.model.Hospital;
import com.example.demo.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public void create(Hospital hospital) {
        hospitalRepository.create(hospital);
    }

    public List<Hospital> readAll() {
        return hospitalRepository.readAll();
    }

    public Hospital findById(String id) {
        return hospitalRepository.findById(id);
    }

    public void update(String id, Hospital updatedHospital) {
        hospitalRepository.update(id, updatedHospital);
    }

    public void delete(String id) {
        hospitalRepository.delete(id);
    }
}
