package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void create(Appointment appointment) {
        appointmentRepository.create(appointment);
    }

    public List<Appointment> readAll() {
        return appointmentRepository.readAll();
    }

    public Appointment findById(String id) {
        return appointmentRepository.findById(id);
    }

    public void update(String id, Appointment updatedAppointment) {
        appointmentRepository.update(id, updatedAppointment);
    }

    public void delete(String id) {
        appointmentRepository.delete(id);
    }
}
