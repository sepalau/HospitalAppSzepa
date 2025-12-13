package com.example.demo.repository;

import com.example.demo.enums.RoomStatus;
import com.example.demo.model.Room;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {


    @Query("SELECT r FROM Room r WHERE " +
            "(:minCapacity IS NULL OR r.capacity >= :minCapacity) AND " +
            "(:status IS NULL OR r.status = :status)")
    List<Room> findRoomsWithFilters(
            @Param("minCapacity") Integer minCapacity,
            @Param("status") RoomStatus status,
            Sort sort);
}