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

    // 1. Listare
    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "room/index";
    }

    // 2. Adaugare
    @GetMapping("/add")
    public String addRoomForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hospitals", hospitalService.getAll());
        return "room/form";
    }

    // 3. Salvare
    @PostMapping("/save")
    public String saveRoom(@ModelAttribute Room room) {
        roomService.save(room);
        return "redirect:/rooms";
    }

    // 4. Editare
    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable Long id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        model.addAttribute("hospitals", hospitalService.getAll());
        return "room/form";
    }

    // --- METODA MODIFICATĂ PENTRU EROAREA 500 ---
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            roomService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Room deleted successfully!");
        } catch (Exception e) {
            // Prindem eroarea de la baza de date
            redirectAttributes.addFlashAttribute("error", "Cannot delete room! It is currently in use or assigned.");
        }
        return "redirect:/rooms";
    }

    // 6. Detalii
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        return "room/details";
    }
}