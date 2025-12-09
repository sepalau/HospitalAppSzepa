package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.service.HospitalService;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // IMPORT NECESAR

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final HospitalService hospitalService;

    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "room/index";
    }

    @GetMapping("/add")
    public String addRoomForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hospitals", hospitalService.getAll());
        return "room/form";
    }

    @PostMapping("/save")
    public String saveRoom(@ModelAttribute Room room) {
        roomService.save(room);
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable Long id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        model.addAttribute("hospitals", hospitalService.getAll());
        return "room/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            roomService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Room deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete room! It is currently in use or assigned.");
        }
        return "redirect:/rooms";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        return "room/details";
    }
}