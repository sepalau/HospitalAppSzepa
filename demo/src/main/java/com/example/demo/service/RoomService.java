package com.example.demo.service;

import com.example.demo.enums.RoomStatus;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> getById(Long id) {
        return roomRepository.findById(id);
    }

    public void save(Room room) {
        roomRepository.save(room);
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
    public List<Room> searchRooms(Integer minCapacity, RoomStatus status, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        return roomRepository.findRoomsWithFilters(minCapacity, status, sort);
    }
}