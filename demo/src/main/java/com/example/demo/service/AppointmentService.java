package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.dto.AppointmentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;

    public Iterable<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    public void saveForm(AppointmentForm form) {
        Appointment a;
        if(form.getId() != null) {
            a = getById(form.getId());
        } else {
            a = new Appointment();
        }
        a.setPatient(patientRepository.findById(form.getPatientId()).orElseThrow());
        a.setDepartment(departmentRepository.findById(form.getDepartmentId()).orElseThrow());
        a.setAdmissionDate(form.getAdmissionDate());
        a.setStatus(form.getStatus());
        appointmentRepository.save(a);
    }

    public AppointmentForm toForm(Appointment a) {
        AppointmentForm form = new AppointmentForm();
        form.setId(a.getId());
        form.setPatientId(a.getPatient().getId());
        form.setDepartmentId(a.getDepartment().getId());
        form.setAdmissionDate(a.getAdmissionDate());
        form.setStatus(a.getStatus());
        return form;
    }
}
