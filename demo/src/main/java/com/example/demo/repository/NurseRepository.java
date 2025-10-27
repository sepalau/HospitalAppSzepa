package com.example.demo.repository;

import com.example.demo.model.Nurse;
import java.util.*;

public class NurseRepository {
    private final Map<String, Nurse> nurses = new HashMap<>();

    public void save(Nurse nurse) {
        nurses.put(nurse.getId(), nurse);
    }

    public List<Nurse> findAll() {
        return new ArrayList<>(nurses.values());
    }

    public Nurse findById(String id) {
        return nurses.get(id);
    }

    public void delete(String id) {
        nurses.remove(id);
    }
}
