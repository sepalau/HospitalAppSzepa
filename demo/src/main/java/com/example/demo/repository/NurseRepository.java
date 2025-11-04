package com.example.demo.repository;

import com.example.demo.model.Nurse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NurseRepository {

    private final List<Nurse> nurses = new ArrayList<>();

    public void create(Nurse nurse) {
        nurses.add(nurse);
    }

    public List<Nurse> readAll() {
        return nurses;
    }

    public Nurse findById(String id) {
        return nurses.stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    public void delete(String id) {
        nurses.removeIf(n -> n.getId().equals(id));
    }
}
