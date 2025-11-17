package com.example.demo.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;

public class InFileRepository<T> implements AbstractRepository<T> {

    private final Path filePath;
    private final ObjectMapper objectMapper;
    private final TypeReference<List<T>> typeReference;
    private final Map<String, T> dataMap;
    private final IdExtractor<T> idExtractor;
    private final String resourcePath;

    public interface IdExtractor<T> {
        String getId(T entity);
    }

    public InFileRepository(String fileName, TypeReference<List<T>> typeReference, IdExtractor<T> idExtractor) {
        this.resourcePath = "data/" + fileName;
        this.filePath = Paths.get("demo/data/" + fileName).toAbsolutePath();
        this.objectMapper = new ObjectMapper();
        this.typeReference = typeReference;
        this.idExtractor = idExtractor;
        this.dataMap = new LinkedHashMap<>();
        initializeFile();
        loadFromFile();
    }

    private void initializeFile() {
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                ClassPathResource resource = new ClassPathResource(resourcePath);
                if (resource.exists()) {
                    try (InputStream in = resource.getInputStream()) {
                        Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
                        return;
                    }
                }
                Files.write(filePath, "[]".getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFromFile() {
        try {
            List<T> list = objectMapper.readValue(filePath.toFile(), typeReference);
            dataMap.clear();
            for (T entity : list) {
                dataMap.put(idExtractor.getId(entity), entity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToFile() {
        try {
            List<T> list = new ArrayList<>(dataMap.values());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(dataMap.values());
    }

    @Override
    public T findById(String id) {
        return dataMap.get(id);
    }

    @Override
    public void create(T entity) {
        dataMap.put(idExtractor.getId(entity), entity);
        saveToFile();
    }

    @Override
    public void update(String id, T entity) {
        dataMap.put(id, entity);
        saveToFile();
    }

    @Override
    public void delete(String id) {
        dataMap.remove(id);
        saveToFile();
    }
}
