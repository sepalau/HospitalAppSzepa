package com.example.demo.repository;

import com.example.demo.model.Nurse;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class NurseRepository extends InFileRepository<Nurse> {
    public NurseRepository() {
        super("src/main/resources/data/nurses.json", Nurse.class);
    }

    public List<Nurse> findByQualification(String qualification) {
        return findAll().stream()
                .filter(n -> qualification.equalsIgnoreCase(n.getQualificationLevel()))
                .toList();
    }

    public List<Nurse> findByDepartment(String departmentId) {
        return findAll().stream()
                .filter(n -> departmentId.equalsIgnoreCase(n.getDepartmentId()))
                .toList();
    }
}