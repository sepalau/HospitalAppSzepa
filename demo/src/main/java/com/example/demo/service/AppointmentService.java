package com.example.demo.service;

import com.example.demo.dto.AppointmentForm;
import com.example.demo.enums.AppointmentStatus;
import com.example.demo.model.Appointment;
import com.example.demo.model.Department;
import com.example.demo.model.MedicalStaff;
import com.example.demo.model.Patient;
import com.example.demo.model.Room; // Import necesar pentru Room
import com.example.demo.repository.*;
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
    private final RoomRepository roomRepository; // Repository-ul este deja injectat

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    // --- LOGICA DE BUSINESS & SALVARE ---
    public void saveForm(AppointmentForm form) {
        Appointment appointment;

        // 1. Verificăm dacă e creare sau editare
        if (form.getId() != null) {
            appointment = appointmentRepository.findById(form.getId())
                    .orElse(new Appointment());
        } else {
            appointment = new Appointment();
        }

        // 2. Setăm datele simple
        appointment.setAdmissionDate(form.getAdmissionDate());

        // 3. Conversie Status (String -> Enum)
        if (form.getStatus() != null && !form.getStatus().isEmpty()) {
            try {
                appointment.setStatus(AppointmentStatus.valueOf(form.getStatus()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Status invalid: " + form.getStatus());
            }
        }

        // 4. Gestionarea relațiilor (Foreign Keys)

        // A. Pacient
        if (form.getPatientId() != null) {
            Patient p = patientRepository.findById(form.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Pacientul selectat (ID " + form.getPatientId() + ") nu există!"));
            appointment.setPatient(p);
        }

        // B. Departament
        if (form.getDepartmentId() != null) {
            Department d = departmentRepository.findById(form.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Departamentul selectat nu există!"));
            appointment.setDepartment(d);
        }

        // C. Personal Medical
        if (form.getMedicalStaffId() != null) {
            MedicalStaff m = medicalStaffRepository.findById(form.getMedicalStaffId())
                    .orElseThrow(() -> new RuntimeException("Medicul selectat nu există!"));
            appointment.setMedicalStaff(m);
        }

        // D. Camera (NOU - Aceasta este partea adăugată pentru legătura cu Room)
        if (form.getRoomId() != null) {
            Room r = roomRepository.findById(form.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Camera selectată nu există!"));
            appointment.setRoom(r);
        }

        // 5. Salvăm
        appointmentRepository.save(appointment);
    }

    // --- CONVERTIRE PENTRU FORMULAR (Entitate -> DTO) ---
    public AppointmentForm toForm(Appointment appointment) {
        AppointmentForm form = new AppointmentForm();

        form.setId(appointment.getId());
        form.setAdmissionDate(appointment.getAdmissionDate());

        // Conversie Status (Enum -> String)
        if (appointment.getStatus() != null) {
            form.setStatus(appointment.getStatus().name());
        }

        // Extragem ID-urile pentru a popula dropdown-urile la Editare
        if (appointment.getPatient() != null) {
            form.setPatientId(appointment.getPatient().getId());
        }
        if (appointment.getDepartment() != null) {
            form.setDepartmentId(appointment.getDepartment().getId());
        }
        if (appointment.getMedicalStaff() != null) {
            form.setMedicalStaffId(appointment.getMedicalStaff().getId());
        }

        // NOU: Extragem ID-ul camerei
        if (appointment.getRoom() != null) {
            form.setRoomId(appointment.getRoom().getId());
        }

        return form;
    }
}