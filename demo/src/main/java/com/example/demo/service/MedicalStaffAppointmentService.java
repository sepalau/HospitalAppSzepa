package com.example.demo.service;

import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.repository.MedicalStaffAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffAppointmentService {

    @Autowired
    private MedicalStaffAppointmentRepository medicalStaffAppointmentRepository;

    public void create(MedicalStaffAppointment appointment) {
        medicalStaffAppointmentRepository.create(appointment);
    }

    public List<MedicalStaffAppointment> readAll() {
        return medicalStaffAppointmentRepository.readAll();
    }

    public MedicalStaffAppointment findById(String id) {
        return medicalStaffAppointmentRepository.findById(id);
    }

    public void update(String id, MedicalStaffAppointment updatedAppointment) {
        medicalStaffAppointmentRepository.update(id, updatedAppointment);
    }

    public void delete(String id) {
        medicalStaffAppointmentRepository.delete(id);
    }
}