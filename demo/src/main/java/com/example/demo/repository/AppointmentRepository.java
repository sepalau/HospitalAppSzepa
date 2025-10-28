package com.example.demo.repository;

import com.example.demo.model.Appointment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppointmentRepository {

    private final List<Appointment> appointments = new ArrayList<Appointment>();

    public void create(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> readAll() {
        return appointments;
    }

    public Appointment findById(String id) {
        for (Appointment a : appointments) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public void update(String id, Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment current = appointments.get(i);
            if (current.getId().equals(id)) {
                appointments.set(i, updatedAppointment);
                return;
            }
        }
    }

    public void delete(String id) {
        Iterator<Appointment> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            Appointment a = iterator.next();
            if (a.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
