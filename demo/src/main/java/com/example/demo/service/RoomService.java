package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository repo;

    public List<Room> getAll() {
        return repo.findAll();
    }

    public Room getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found: " + id));
    }

    public Room save(Room room) {
        return repo.save(room);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
