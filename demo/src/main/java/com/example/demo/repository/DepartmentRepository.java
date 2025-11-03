package com.example.demo.repository;

import com.example.demo.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class DepartmentRepository {

    private final List<Department> departments = new ArrayList<>();

    public void create(Department department) {
        departments.add(department);
    }

    public List<Department> readAll() {
        return departments;
    }

    public Department findById(String id) {
        for (Department d : departments) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    public void delete(String id) {
        Iterator<Department> iterator = departments.iterator();
        while (iterator.hasNext()) {
            Department d = iterator.next();
            if (d.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
