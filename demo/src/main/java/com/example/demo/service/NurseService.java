package com.example.demo.service;

import com.example.demo.model.Nurse;
import com.example.demo.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    public void create(Nurse nurse) {
        nurseRepository.create(nurse);
    }

    public List<Nurse> readAll() {
        return nurseRepository.readAll();
    }

    public Nurse findById(String id) {
        return nurseRepository.findById(id);
    }

    public void update(String id, Nurse updatedNurse) {
        nurseRepository.update(id, updatedNurse);
    }

    public void delete(String id) {
        nurseRepository.delete(id);
    }
}