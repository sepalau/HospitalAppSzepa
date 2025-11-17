package com.example.demo.repository;

import com.example.demo.model.Nurse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class NurseRepository extends InFileRepository<Nurse> {
    public NurseRepository() {
        super("nurse.json", new TypeReference<>() {}, Nurse::getId);
    }
}
