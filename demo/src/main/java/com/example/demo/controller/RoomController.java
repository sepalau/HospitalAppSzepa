package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.service.HospitalService;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final HospitalService hospitalService;

    // 1. Listare toate camerele
    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "room/index";
    }

    // 2. Formular Adăugare Cameră
    @GetMapping("/add")
    public String addRoomForm(Model model) {
        model.addAttribute("room", new Room());
        // Trimitem lista de spitale pentru dropdown-ul din formular
        model.addAttribute("hospitals", hospitalService.getAll());
        return "room/form";
    }

    // 3. Salvare (Create sau Update)
    @PostMapping("/save")
    public String saveRoom(@ModelAttribute Room room) {
        roomService.save(room);
        return "redirect:/rooms";
    }

    // 4. Formular Editare
    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable Long id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        // Avem nevoie de lista de spitale și la editare
        model.addAttribute("hospitals", hospitalService.getAll());
        return "room/form";
    }

    // 5. Ștergere
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.delete(id);
        return "redirect:/rooms";
    }

    // 6. Detalii (Opțional, dacă ai pagina details.html)
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        return "room/details";
    }
}
//sepa