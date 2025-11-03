package com.example.demo.repository;

import com.example.demo.model.Nurse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NurseRepository {

    private final List<Nurse> nurses = new ArrayList<Nurse>();

    public void create(Nurse nurse) {
        nurses.add(nurse);
    }

    public List<Nurse> readAll() {
        return nurses;
    }

    public Nurse findById(String id) {
        for (Nurse n : nurses) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        return null;
    }

    public void update(String id, Nurse updatedNurse) {
        for (int i = 0; i < nurses.size(); i++) {
            if (nurses.get(i).getId().equals(id)) {
                nurses.set(i, updatedNurse);
                return;
            }
        }
    }

    public void delete(String id) {
        Iterator<Nurse> iterator = nurses.iterator();
        while (iterator.hasNext()) {
            Nurse n = iterator.next();
            if (n.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
