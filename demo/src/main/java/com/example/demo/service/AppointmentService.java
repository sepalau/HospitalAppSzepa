package com.example.demo.service;

import com.example.demo.dto.AppointmentForm;
import com.example.demo.enums.AppointmentStatus; // IMPORT IMPORTANT
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
    private final MedicalStaffRepository medicalStaffRepository;

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    // --- SALVARE (DTO -> Entitate) ---
    public void saveForm(AppointmentForm form) {
        Appointment appointment;

        if (form.getId() != null) {
            appointment = appointmentRepository.findById(form.getId())
                    .orElse(new Appointment());
        } else {
            appointment = new Appointment();
        }

        appointment.setAdmissionDate(form.getAdmissionDate());

        // CORECTAT: Convertim din String (Form) în Enum (Entitate)
        if (form.getStatus() != null && !form.getStatus().isEmpty()) {
            appointment.setStatus(AppointmentStatus.valueOf(form.getStatus()));
        }

        // Mapare Relații
        if (form.getPatientId() != null) {
            Patient p = patientRepository.findById(form.getPatientId()).orElse(null);
            appointment.setPatient(p);
        }

        if (form.getDepartmentId() != null) {
            Department d = departmentRepository.findById(form.getDepartmentId()).orElse(null);
            appointment.setDepartment(d);
        }

        if (form.getMedicalStaffId() != null) {
            MedicalStaff m = medicalStaffRepository.findById(form.getMedicalStaffId()).orElse(null);
            appointment.setMedicalStaff(m);
        }

        appointmentRepository.save(appointment);
    }

    // --- CONVERTIRE (Entitate -> DTO) ---
    public AppointmentForm toForm(Appointment appointment) {
        AppointmentForm form = new AppointmentForm();

        form.setId(appointment.getId());
        form.setAdmissionDate(appointment.getAdmissionDate());

        // CORECTAT: Convertim din Enum (Entitate) în String (Form)
        if (appointment.getStatus() != null) {
            form.setStatus(appointment.getStatus().name());
        }

        if (appointment.getPatient() != null) {
            form.setPatientId(appointment.getPatient().getId());
        }
        if (appointment.getDepartment() != null) {
            form.setDepartmentId(appointment.getDepartment().getId());
        }
        if (appointment.getMedicalStaff() != null) {
            form.setMedicalStaffId(appointment.getMedicalStaff().getId());
        }

        return form;
    }
}