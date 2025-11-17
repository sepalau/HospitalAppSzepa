package com.example.demo.service;

import com.example.demo.model.Nurse;
import com.example.demo.repository.NurseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService {

    private final NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    public void create(Nurse nurse) {
        nurseRepository.create(nurse);
    }

    public List<Nurse> readAll() {
        return nurseRepository.findAll();
    }

    public Nurse findById(String id) {
        return nurseRepository.findById(id);
    }

    public void update(String id, Nurse nurse) {
        nurseRepository.update(id, nurse);
    }

    public void delete(String id) {
        nurseRepository.delete(id);
    }
}
