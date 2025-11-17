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
    public String list(Model model) {
        model.addAttribute("rooms", roomService.readAll());
        return "room/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("room", new Room());
        return "room/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("room", roomService.findById(id));
        return "room/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Room room) {

        // NEW → create
        if (room.getId() == null || room.getId().isEmpty()) {
            room.setId("Room" + System.currentTimeMillis());
            roomService.create(room);
        }
        // EDIT → update
        else {
            roomService.update(room.getId(), room);
        }

        return "redirect:/rooms";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        roomService.delete(id);
        return "redirect:/rooms";
    }
}
