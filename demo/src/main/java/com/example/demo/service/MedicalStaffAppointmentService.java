package com.example.demo.service;

import com.example.demo.dto.MedicalStaffAssignmentForm;
import com.example.demo.model.Appointment;
import com.example.demo.model.MedicalStaff;
import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.MedicalStaffAppointmentRepository;
import com.example.demo.repository.MedicalStaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalStaffAppointmentService {

    private final MedicalStaffAppointmentRepository msaRepository;
    private final MedicalStaffRepository medicalStaffRepository;
    private final AppointmentRepository appointmentRepository;

    public List<MedicalStaffAppointment> getAll() {
        return msaRepository.findAll();
    }

    public MedicalStaffAppointment getById(Long id) {
        return msaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
    }

    public void saveForm(MedicalStaffAssignmentForm form) {
        MedicalStaffAppointment assignment;

        if (form.getId() != null) {
            assignment = msaRepository.findById(form.getId()).orElse(new MedicalStaffAppointment());
        } else {
            assignment = new MedicalStaffAppointment();
        }

        // CORECTAT: setMedicalStaff (era setStaff)
        if (form.getStaffId() != null) {
            MedicalStaff staff = medicalStaffRepository.findById(form.getStaffId()).orElse(null);
            assignment.setMedicalStaff(staff);
        }

        // appointment ramane la fel
        if (form.getAppointmentId() != null) {
            Appointment appointment = appointmentRepository.findById(form.getAppointmentId()).orElse(null);
            assignment.setAppointment(appointment);
        }

        msaRepository.save(assignment);
    }

    public void delete(Long id) {
        msaRepository.deleteById(id);
    }

    public MedicalStaffAssignmentForm toForm(MedicalStaffAppointment assignment) {
        MedicalStaffAssignmentForm form = new MedicalStaffAssignmentForm();
        form.setId(assignment.getId());

        // CORECTAT: getMedicalStaff (era getStaff)
        if (assignment.getMedicalStaff() != null) {
            form.setStaffId(assignment.getMedicalStaff().getId());
        }
        if (assignment.getAppointment() != null) {
            form.setAppointmentId(assignment.getAppointment().getId());
        }
        return form;
    }
}