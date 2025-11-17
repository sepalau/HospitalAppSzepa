package com.example.demo.repository;

import com.example.demo.model.Room;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RoomRepository extends InFileRepository<Room> {
    public RoomRepository() {
        super("room.json", new TypeReference<>() {}, Room::getId);
    }
}
