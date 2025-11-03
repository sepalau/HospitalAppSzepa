package com.example.demo.repository;

import com.example.demo.model.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    public void create(Room room) {
        rooms.add(room);
    }

    public List<Room> readAll() {
        return rooms;
    }

    public Room findById(String id) {
        for (Room room : rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    public void delete(String id) {
        Iterator<Room> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            Room room = iterator.next();
            if (room.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
