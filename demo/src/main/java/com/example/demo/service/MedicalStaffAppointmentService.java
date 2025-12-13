package com.example.demo.service;

import com.example.demo.dto.MedicalStaffAssignmentForm;
import com.example.demo.model.Appointment;
import com.example.demo.model.MedicalStaff;
import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.MedicalStaffAppointmentRepository;
import com.example.demo.repository.MedicalStaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort; // <--- IMPORT NOU NECESAR
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalStaffAppointmentService {

    private final MedicalStaffAppointmentRepository msaRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalStaffRepository medicalStaffRepository;

    public List<MedicalStaffAppointment> getAll() {
        return msaRepository.findAll();
    }

    public MedicalStaffAppointment getById(Long id) {
        return msaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));
    }

    public void delete(Long id) {
        msaRepository.deleteById(id);
    }

    // --- SALVARE CU VALIDARE DE BUSINESS (Păstrat din codul tău) ---
    public void saveForm(MedicalStaffAssignmentForm form) {
        MedicalStaffAppointment assignment;

        // 1. Verificăm dacă e creare sau editare
        if (form.getId() != null) {
            // Editare: luăm existentul
            assignment = msaRepository.findById(form.getId())
                    .orElse(new MedicalStaffAppointment());
        } else {
            // Creare: Verificăm UNICITATEA (Business Logic)
            boolean exists = msaRepository.existsByAppointmentIdAndMedicalStaffId(
                    form.getAppointmentId(), form.getStaffId());

            if (exists) {
                throw new RuntimeException("Acest cadru medical este deja asignat la această programare!");
            }
            assignment = new MedicalStaffAppointment();
        }

        // 2. Validare Existență Programare
        if (form.getAppointmentId() != null) {
            Appointment app = appointmentRepository.findById(form.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Programarea selectată nu există!"));
            assignment.setAppointment(app);
        }

        // 3. Validare Existență Cadru Medical
        if (form.getStaffId() != null) {
            MedicalStaff staff = medicalStaffRepository.findById(form.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Cadrul medical selectat nu există!"));
            assignment.setMedicalStaff(staff);
        }

        // 4. Salvare
        msaRepository.save(assignment);
    }

    // --- CONVERSIE ENTITATE -> DTO (Păstrat din codul tău) ---
    public MedicalStaffAssignmentForm toForm(MedicalStaffAppointment entity) {
        MedicalStaffAssignmentForm form = new MedicalStaffAssignmentForm();

        form.setId(entity.getId());

        if (entity.getMedicalStaff() != null) {
            form.setStaffId(entity.getMedicalStaff().getId());
        }

        if (entity.getAppointment() != null) {
            form.setAppointmentId(entity.getAppointment().getId());
        }

        return form;
    }

    // =========================================================================
    // --- METODA NOUĂ PENTRU PROJEKT 5 (FILTRARE & SORTARE) ---
    // =========================================================================
    public List<MedicalStaffAppointment> search(String staffName, Long appointmentId, String sortBy, String sortDir) {

        // 1. Configurare direcție sortare
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        // 2. Maparea câmpurilor de sortare (pentru relații)
        String sortProperty = "id"; // default

        if ("staff".equals(sortBy)) {
            sortProperty = "medicalStaff.name"; // Sortăm după numele medicului
        } else if ("appointment".equals(sortBy)) {
            sortProperty = "appointment.id";    // Sortăm după ID-ul programării
        } else {
            sortProperty = sortBy;              // Sortare standard (ex: id)
        }

        Sort sort = Sort.by(direction, sortProperty);

        // 3. Curățare filtru text (string gol devine null)
        String filterName = (staffName != null && !staffName.trim().isEmpty()) ? staffName.trim() : null;

        // 4. Apel repository (Asigură-te că metoda findWithFilters există în Repository!)
        return msaRepository.findWithFilters(filterName, appointmentId, sort);
    }
}