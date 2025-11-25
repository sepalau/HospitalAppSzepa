package com.example.demo.service;

import com.example.demo.model.AppointmentForm;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;

    public Iterable<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id).orElseThrow();
    }

    // pentru edit
    public AppointmentForm getFormById(Long id) {
        Appointment a = appointmentRepository.findById(id).orElseThrow();

        AppointmentForm f = new AppointmentForm();
        f.setId(a.getId());
        f.setPatientId(a.getPatient().getId());
        f.setDepartmentId(a.getDepartment().getId());
        f.setMedicalStaffId(a.getMedicalStaff().getId());
        f.setAdmissionDate(a.getAdmissionDate());
        f.setStatus(a.getStatus());

        return f;
    }

    public void saveForm(AppointmentForm form) {
        Appointment a = new Appointment();
        a.setId(form.getId());

        a.setPatient(patientRepository.findById(form.getPatientId()).orElseThrow());
        a.setDepartment(departmentRepository.findById(form.getDepartmentId()).orElseThrow());

        // staff poate fi doctor sau nurse
        MedicalStaff staff = doctorRepository.findById(form.getMedicalStaffId())
                .map(d -> (MedicalStaff)d)
                .orElseGet(() -> nurseRepository.findById(form.getMedicalStaffId()).orElseThrow());

        a.setMedicalStaff(staff);

        a.setAdmissionDate(form.getAdmissionDate());
        a.setStatus(form.getStatus());

        appointmentRepository.save(a);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
