package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.model.MedicalStaff;
import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.MedicalStaffAppointmentRepository;
import com.example.demo.repository.MedicalStaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffAppointmentService {

    private final MedicalStaffAppointmentRepository repository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalStaffRepository medicalStaffRepository;

    public MedicalStaffAppointmentService(MedicalStaffAppointmentRepository repository,
                                          AppointmentRepository appointmentRepository,
                                          MedicalStaffRepository medicalStaffRepository) {
        this.repository = repository;
        this.appointmentRepository = appointmentRepository;
        this.medicalStaffRepository = medicalStaffRepository;
    }

    public List<MedicalStaffAppointment> getAll() {
        return repository.findAll();
    }

    public MedicalStaffAppointment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));
    }

    public void save(MedicalStaffAppointment link, Long appointmentId, Long staffId) {
        if (appointmentId != null) {
            Appointment app = appointmentRepository.findById(appointmentId).orElse(null);
            link.setAppointment(app);
        }
        if (staffId != null) {
            MedicalStaff staff = medicalStaffRepository.findById(staffId).orElse(null);
            link.setMedicalStaff(staff);
        }
        repository.save(link);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}