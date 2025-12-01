package com.example.demo.service;

import com.example.demo.dto.AppointmentForm;
import com.example.demo.model.Appointment;
import com.example.demo.model.Department;
import com.example.demo.model.MedicalStaff;
import com.example.demo.model.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.MedicalStaffRepository;
import com.example.demo.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;

    // Injectăm repository-ul pe care tocmai l-am creat
    private final MedicalStaffRepository medicalStaffRepository;

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public void saveForm(AppointmentForm form) {
        Appointment appointment;

        // Dacă are ID, este editare. Dacă nu, este creare.
        if (form.getId() != null) {
            appointment = appointmentRepository.findById(form.getId())
                    .orElse(new Appointment());
        } else {
            appointment = new Appointment();
        }

        // Mapare câmpuri simple
        appointment.setAdmissionDate(form.getAdmissionDate());
        appointment.setStatus(form.getStatus());

        // 1. Mapare Pacient
        if (form.getPatientId() != null) {
            Patient patient = patientRepository.findById(form.getPatientId()).orElse(null);
            appointment.setPatient(patient);
        }

        // 2. Mapare Departament
        if (form.getDepartmentId() != null) {
            Department department = departmentRepository.findById(form.getDepartmentId()).orElse(null);
            appointment.setDepartment(department);
        }

        // 3. Mapare Medical Staff (AICI ERA PROBLEMA TA)
        if (form.getMedicalStaffId() != null) {
            MedicalStaff staff = medicalStaffRepository.findById(form.getMedicalStaffId()).orElse(null);
            appointment.setMedicalStaff(staff);
        }

        appointmentRepository.save(appointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Transformă Entitatea în Formular (pentru Editare)
    public AppointmentForm toForm(Appointment appointment) {
        AppointmentForm form = new AppointmentForm();
        form.setId(appointment.getId());
        form.setAdmissionDate(appointment.getAdmissionDate());
        form.setStatus(appointment.getStatus());

        if (appointment.getPatient() != null) {
            form.setPatientId(appointment.getPatient().getId());
        }
        if (appointment.getDepartment() != null) {
            form.setDepartmentId(appointment.getDepartment().getId());
        }
        // Setăm ID-ul medicului în formular
        if (appointment.getMedicalStaff() != null) {
            form.setMedicalStaffId(appointment.getMedicalStaff().getId());
        }

        return form;
    }
}