package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class AppointmentRepository {

    private final List<Appointment> appointments = new ArrayList<>();

    public void create(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> readAll() {
        return appointments;
    }

    public Appointment findById(String id) {
        return appointments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void delete(String id) {
        Iterator<Appointment> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
