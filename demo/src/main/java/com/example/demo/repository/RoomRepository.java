package com.example.demo.repository;

import com.example.demo.model.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class RoomRepository {

    private final List<Room> rooms = new ArrayList<Room>();

    public void create(Room room) {
        rooms.add(room);
    }

    public List<Room> readAll() {
        return rooms;
    }

    public Room findById(String id) {
        for (Room r : rooms) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    public void update(String id, Room updatedRoom) {
        for (int i = 0; i < rooms.size(); i++) {
            Room current = rooms.get(i);
            if (current.getId().equals(id)) {
                rooms.set(i, updatedRoom);
                return;
            }
        }
    }

    public void delete(String id) {
        Iterator<Room> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            Room r = iterator.next();
            if (r.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
