package com.example.inopolis.service;

import com.example.inopolis.model.NoteDTO;

import java.util.List;

public interface NoteService {
    void delete(Integer id);
    void create (NoteDTO note);
    void update (Integer id, NoteDTO note);
    NoteDTO getById (Integer id);
    List<NoteDTO> getAllNotes();
}
