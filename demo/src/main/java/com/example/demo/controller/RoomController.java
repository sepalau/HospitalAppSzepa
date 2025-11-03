package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomService.readAll());
        return "room/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("room", new Room());
        return "room/form";
    }

    @PostMapping
    public String createRoom(@ModelAttribute Room room) {
        roomService.create(room);
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/delete")
    public String deleteRoom(@PathVariable String id) {
        roomService.delete(id);
        return "redirect:/rooms";
    }
}
