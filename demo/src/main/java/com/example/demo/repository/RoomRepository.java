package com.example.demo.repository;

import com.example.demo.model.Room;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RoomRepository extends InFileRepository<Room> {
    public RoomRepository() {
        super("src/main/resources/data/rooms.json", Room.class);
    }

    public List<Room> findAvailableRooms() {
        return findAll().stream()
                .filter(r -> "Available".equalsIgnoreCase(r.getStatus()))
                .toList();
    }

    public List<Room> findByHospitalId(String hospitalId) {
        return findAll().stream()
                .filter(r -> hospitalId.equalsIgnoreCase(r.getHospitalId()))
                .toList();
    }
}