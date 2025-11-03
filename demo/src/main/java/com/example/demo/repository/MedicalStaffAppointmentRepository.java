package com.example.demo.repository;

import com.example.demo.model.MedicalStaffAppointment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class MedicalStaffAppointmentRepository {

    private final List<MedicalStaffAppointment> appointments = new ArrayList<MedicalStaffAppointment>();

    public void create(MedicalStaffAppointment appointment) {
        appointments.add(appointment);
    }

    public List<MedicalStaffAppointment> readAll() {
        return appointments;
    }

    public MedicalStaffAppointment findById(String id) {
        for (MedicalStaffAppointment a : appointments) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public void update(String id, MedicalStaffAppointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getId().equals(id)) {
                appointments.set(i, updatedAppointment);
                return;
            }
        }
    }

    public void delete(String id) {
        Iterator<MedicalStaffAppointment> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            MedicalStaffAppointment a = iterator.next();
            if (a.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
