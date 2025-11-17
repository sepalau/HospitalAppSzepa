package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void create(Room room) {
        roomRepository.create(room);
    }

    public List<Room> readAll() {
        return roomRepository.findAll();
    }

    public Room findById(String id) {
        return roomRepository.findById(id);
    }

    public void update(String id, Room updatedRoom) {
        roomRepository.update(id, updatedRoom);
    }

    public void delete(String id) {
        roomRepository.delete(id);
    }
}
