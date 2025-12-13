package com.example.demo.controller;

import com.example.demo.enums.QualificationLevel;
import com.example.demo.model.Nurse;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.NurseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/nurses")
@RequiredArgsConstructor
public class NurseController {

    private final NurseService nurseService;
    private final DepartmentService departmentService; // Necesar pentru dropdown departamente la Add/Edit

    // 1. Listare cu Filtre și Sortare
    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) QualificationLevel level,
                       @RequestParam(defaultValue = "name") String sortBy,
                       @RequestParam(defaultValue = "asc") String sortDir) {

        // Căutare
        List<Nurse> nurses = nurseService.searchNurses(name, level, sortBy, sortDir);
        model.addAttribute("nurses", nurses);

        // Trimitem parametrii înapoi pentru UI (Sticky inputs)
        model.addAttribute("name", name);
        model.addAttribute("level", level);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        // Helper pentru inversarea sortării în link-urile din tabel
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "nurse/index";
    }

    // 2. Formular Adăugare
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        model.addAttribute("departments", departmentService.getAll());
        return "nurse/form";
    }

    // 3. Salvare
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("nurse") Nurse nurse,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAll());
            return "nurse/form";
        }
        nurseService.save(nurse);
        redirectAttributes.addFlashAttribute("success", "Asistentă salvată cu succes!");
        return "redirect:/nurses";
    }

    // 4. Editare
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Nurse nurse = nurseService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Nurse ID:" + id));

        model.addAttribute("nurse", nurse);
        model.addAttribute("departments", departmentService.getAll());
        return "nurse/form";
    }

    // 5. Ștergere
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            nurseService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Asistentă ștearsă cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Nu se poate șterge (posibil legată de programări).");
        }
        return "redirect:/nurses";
    }
}