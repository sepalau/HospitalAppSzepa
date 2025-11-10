package com.example.demo.repository;

import java.util.List;

public interface AbstractRepository<T> {
    List<T> findAll();
    T findById(String id);
    void create(T entity);
    void update(String id, T entity);
    void delete(String id);
}