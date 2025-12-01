package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.model.Department;
import com.example.demo.model.MedicalStaff;
import com.example.demo.model.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.MedicalStaffRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final MedicalStaffRepository medicalStaffRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DepartmentRepository departmentRepository,
                              MedicalStaffRepository medicalStaffRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
        this.medicalStaffRepository = medicalStaffRepository;
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    // Salvam folosind ID-urile trimise din formular
    public void save(Appointment appointment, Long patientId, Long departmentId, Long staffId) {

        if (patientId != null) {
            Patient p = patientRepository.findById(patientId).orElse(null);
            appointment.setPatient(p);
        }

        if (departmentId != null) {
            Department d = departmentRepository.findById(departmentId).orElse(null);
            appointment.setDepartment(d);
        }

        if (staffId != null) {
            MedicalStaff m = medicalStaffRepository.findById(staffId).orElse(null);
            appointment.setMedicalStaff(m);
        }

        appointmentRepository.save(appointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}