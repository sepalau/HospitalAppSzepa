package com.example.demo.controller;

import com.example.demo.enums.RoomStatus;
import com.example.demo.model.Room;
import com.example.demo.service.HospitalService;
import com.example.demo.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final HospitalService hospitalService;

    // 1. Listare cu SORTARE și FILTRARE
    @GetMapping
    public String listRooms(Model model,
                            @RequestParam(required = false) Integer minCapacity,
                            @RequestParam(required = false) RoomStatus status,
                            @RequestParam(defaultValue = "number") String sortBy, // Sortare default după număr
                            @RequestParam(defaultValue = "asc") String sortDir) { // Direcție default ASC

        // Obținem lista filtrată și sortată
        List<Room> rooms = roomService.searchRooms(minCapacity, status, sortBy, sortDir);
        model.addAttribute("rooms", rooms);

        // Trimitem parametrii înapoi în pagină ca să rămână selectați în formular
        model.addAttribute("minCapacity", minCapacity);
        model.addAttribute("status", status);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        // Calculăm direcția opusă pentru link-urile din tabel (dacă e asc, următorul click va fi desc)
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "room/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hospitals", hospitalService.getAll());
        return "room/form";
    }

    // 3. Salvare
    @PostMapping("/save")
    public String saveRoom(@Valid @ModelAttribute("room") Room room,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("hospitals", hospitalService.getAll());
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

    // 5. Detalii
    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        Room room = roomService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        model.addAttribute("room", room);
        return "room/details";
    }

    // 6. Ștergere
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.delete(id);
        return "redirect:/rooms";
    }
}