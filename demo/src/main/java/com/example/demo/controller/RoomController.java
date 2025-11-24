package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rooms", service.readAll());
        return "room/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("room", new Room());
        return "room/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("room") Room room,
                         BindingResult result) {

        if (result.hasErrors())
            return "room/form";

        service.create(room);
        return "redirect:/rooms";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("room", service.findById(id));
        return "room/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id,
                         @Valid @ModelAttribute("room") Room room,
                         BindingResult result) {

        if (result.hasErrors())
            return "room/form";

        service.update(id, room);
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/rooms";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("room", service.findById(id));
        return "room/details";
    }
}