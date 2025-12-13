package com.example.demo.service;

import com.example.demo.dto.AppointmentForm;
import com.example.demo.enums.AppointmentStatus;
import com.example.demo.model.Appointment;
import com.example.demo.model.Department;
import com.example.demo.model.MedicalStaff;
import com.example.demo.model.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.MedicalStaffRepository;
import com.example.demo.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final MedicalStaffRepository medicalStaffRepository;

    // --- METODA NOUĂ PENTRU SORTARE ȘI FILTRARE (PDF 5) ---
    public List<Appointment> getAll(String keyword, String sortField, String sortDir) {
        // 1. Stabilim direcția sortării
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        // 2. Dacă avem un cuvânt cheie, filtrăm
        if (keyword != null && !keyword.isEmpty()) {
            return appointmentRepository.searchByKeyword(keyword, sort);
        }

        // 3. Altfel, returnăm tot, dar sortat
        return appointmentRepository.findAll(sort);
    }

    // Metoda veche fără parametri (pentru compatibilitate cu alte controllere, dacă e cazul)
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

    // --- SALVARE CU VALIDARE DE BUSINESS ---
    public void saveForm(AppointmentForm form) {
        Appointment appointment;

        if (form.getId() != null) {
            appointment = appointmentRepository.findById(form.getId())
                    .orElse(new Appointment());
        } else {
            appointment = new Appointment();
        }

        appointment.setAdmissionDate(form.getAdmissionDate());

        if (form.getStatus() != null && !form.getStatus().isEmpty()) {
            try {
                appointment.setStatus(AppointmentStatus.valueOf(form.getStatus()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Status invalid: " + form.getStatus());
            }
        }

        // Validare Business: Verificăm dacă entitățile există
        if (form.getPatientId() != null) {
            Patient p = patientRepository.findById(form.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Pacientul selectat nu există!"));
            appointment.setPatient(p);
        }

        if (form.getDepartmentId() != null) {
            Department d = departmentRepository.findById(form.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Departamentul selectat nu există!"));
            appointment.setDepartment(d);
        }

        if (form.getMedicalStaffId() != null) {
            MedicalStaff m = medicalStaffRepository.findById(form.getMedicalStaffId())
                    .orElseThrow(() -> new RuntimeException("Medicul selectat nu există!"));
            appointment.setMedicalStaff(m);
        }

        appointmentRepository.save(appointment);
    }

    // --- CONVERTIRE (Entitate -> DTO) ---
    public AppointmentForm toForm(Appointment appointment) {
        AppointmentForm form = new AppointmentForm();
        form.setId(appointment.getId());
        form.setAdmissionDate(appointment.getAdmissionDate());

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