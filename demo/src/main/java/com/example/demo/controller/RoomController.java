package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.service.HospitalService;
import com.example.demo.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final HospitalService hospitalService; // Avem nevoie de asta pentru dropdown-ul cu spitale

    @Autowired
    public RoomController(RoomService roomService, HospitalService hospitalService) {
        this.roomService = roomService;
        this.hospitalService = hospitalService;
    }

    // 1. Afișare listă camere
    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "room/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hospitals", hospitalService.getAll()); // Trimitem lista de spitale
        return "room/form";
    }

    // 3. Salvare (Create sau Update) cu VALIDARE
    @PostMapping("/save")
    public String saveRoom(@Valid @ModelAttribute("room") Room room,
                           BindingResult result,
                           Model model) {
        // Dacă sunt erori de validare, rămânem pe pagină și afișăm erorile
        if (result.hasErrors()) {
            model.addAttribute("hospitals", hospitalService.getAll()); // Reîncărcăm spitalele
            return "room/form";
        }

        roomService.save(room);
        return "redirect:/rooms";
    }

    // 4. Formular Editare
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Room room = roomService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));

        model.addAttribute("room", room);
        model.addAttribute("hospitals", hospitalService.getAll());
        return "room/form";
    }

    // 5. Ștergere
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.delete(id);
        return "redirect:/rooms";
    }

    // 6. Afișare Detalii (NOU)
    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        Room room = roomService.getById(id) // Folosește metoda corectată care returnează Optional
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));

        model.addAttribute("room", room);
        return "room/details"; // Aceasta este pagina html pe care o vom crea
    }
}