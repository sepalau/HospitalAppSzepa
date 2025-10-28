package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository roomRepository = new RoomRepository();

    @PostMapping
    public String create(@RequestBody Room room) {
        roomRepository.create(room);
        return "Room created successfully.";
    }

    @GetMapping
    public List<Room> readAll() {
        return roomRepository.readAll();
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable String id) {
        return roomRepository.findById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Room updatedRoom) {
        roomRepository.update(id, updatedRoom);
        return "Room updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        roomRepository.delete(id);
        return "Room deleted successfully.";
    }
}
