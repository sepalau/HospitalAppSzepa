package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public void create(Room room) {
        repository.create(room);
    }

    public List<Room> findAll() {
        return repository.findAll();
    }

    public Room findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
