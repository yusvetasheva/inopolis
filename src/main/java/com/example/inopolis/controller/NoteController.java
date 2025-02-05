package com.example.inopolis.controller;

import com.example.inopolis.model.NoteDTO;
import com.example.inopolis.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/notes")
public class NoteController {

    @Autowired
    NoteService service;

    @PostMapping(value = "/create")
    public ResponseEntity<String> create(@RequestBody @Valid NoteDTO noteDTO){
        service.create(noteDTO);
        return ResponseEntity.ok("Заметка создана");
    }

    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<NoteDTO> getById(@PathVariable Integer id){
        return ResponseEntity.of(Optional.of(service.getById(id)));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok("Зачетка удалена");
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody NoteDTO noteDTO){
        service.update(id, noteDTO);
        return ResponseEntity.ok("Заявка обновлена");
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<NoteDTO>> getAll(){
        return ResponseEntity.of(Optional.ofNullable(service.getAllNotes()));
    }
}
