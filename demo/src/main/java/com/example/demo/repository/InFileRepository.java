package com.example.demo.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class InFileRepository<T> implements AbstractRepository<T> {
    private final String filePath;
    private final Class<T> type;
    private final Gson gson = new Gson();
    private List<T> elements = new ArrayList<>();

    public InFileRepository(String filePath, Class<T> type) {
        this.filePath = filePath;
        this.type = type;
        loadFromFile();
    }

    @Override
    public List<T> findAll() {
        return elements;
    }

    @Override
    public T findById(String id) {
        return elements.stream()
                .filter(e -> Objects.equals(getId(e), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void create(T entity) {
        elements.add(entity);
        saveToFile();
    }

    @Override
    public void update(String id, T entity) {
        delete(id);
        elements.add(entity);
        saveToFile();
    }

    @Override
    public void delete(String id) {
        elements.removeIf(e -> Objects.equals(getId(e), id));
        saveToFile();
    }

    /** === JSON File I/O === */
    protected void saveToFile() {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(elements, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error saving to JSON file: " + filePath, e);
        }
    }

    protected void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;
        try (Reader reader = new FileReader(file)) {
            Type listType = TypeToken.getParameterized(List.class, type).getType();
            List<T> data = gson.fromJson(reader, listType);
            if (data != null) elements = data;
        } catch (IOException e) {
            throw new RuntimeException("Error loading JSON file: " + filePath, e);
        }
    }

    private String getId(T entity) {
        try {
            return (String) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            throw new RuntimeException("Entity " + entity.getClass().getSimpleName() + " must have a getId() method");
        }
    }
}
