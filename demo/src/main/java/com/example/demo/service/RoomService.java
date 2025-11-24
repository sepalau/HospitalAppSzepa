package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository repo;

    public RoomService(RoomRepository repo) {
        this.repo = repo;
    }

    public List<Room> readAll() {
        return repo.findAll();
    }

    public Room findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void create(Room room) {
        repo.save(room);
    }

    public void update(String id, Room room) {
        repo.save(room);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}