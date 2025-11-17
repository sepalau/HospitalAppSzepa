package com.example.demo.repository;

import com.example.demo.model.Hospital;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HospitalRepository {

    private static final String FILE_PATH = "data/hospitals.json";
    private final Gson gson = new Gson();
    private List<Hospital> hospitals;

    public HospitalRepository() {
        this.hospitals = loadFromFile();
    }

    // ==========================
    //       FILE METHODS
    // ==========================

    private List<Hospital> loadFromFile() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Hospital>>() {}.getType();
            List<Hospital> data = gson.fromJson(reader, listType);
            return data != null ? data : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(hospitals, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ==========================
    //       CRUD METHODS
    // ==========================

    public void create(Hospital hospital) {
        hospitals.add(hospital);
        saveToFile();
    }

    public List<Hospital> readAll() {
        return hospitals;
    }

    public Hospital findById(String id) {
        return hospitals.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(String id, Hospital updated) {
        Hospital existing = findById(id);
        if (existing != null) {
            hospitals.remove(existing);
            hospitals.add(updated);
            saveToFile();
        }
    }

    public void delete(String id) {
        hospitals.removeIf(h -> h.getId().equals(id));
        saveToFile();
    }
}
