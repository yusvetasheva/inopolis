package com.example.inopolis.service;

import com.example.inopolis.model.NoteDTO;
import com.example.inopolis.model.NoteEntity;
import com.example.inopolis.repository.NoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository repository;

    @Override
    @Transactional
    public void create(NoteDTO note) {
        if (note.getDateAndTime() == null)
            note.setDateAndTime(LocalDate.now());
        NoteEntity entity = dtoToEntity(note);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Integer id, NoteDTO note) {
        if (id == null) throw new IllegalArgumentException("id в методе update не может быть null");
        Optional<NoteEntity> existingNote = repository.findById(id);
        if (existingNote.isPresent()) {
            NoteEntity updatedNote = dtoToEntity(note);
            updatedNote.setId(id);
            repository.save(updatedNote);
        } else {
            throw new NoSuchElementException("Нет элемента с id = " + id);
        }
    }

    @Override
    public NoteDTO getById(Integer id) {
        if (id == null) throw new IllegalArgumentException("id в методе getById не может быть null");
        return repository.findById(id)
                .map(this::entityToDto)
                .orElse(null); // или Optional<NoteDTO>
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        List<NoteEntity> entityList = repository.findAll();
        if (entityList == null || entityList.isEmpty()) return Collections.emptyList();
        return entityList.stream().map(this::entityToDto).toList();
    }

    public NoteEntity dtoToEntity(NoteDTO noteDTO) {
        return NoteEntity.builder()
                .id(noteDTO.getId())
                .topic(noteDTO.getTopic())
                .dateAndTime(noteDTO.getDateAndTime())
                .fullText(noteDTO.getFullText())
                .build();
    }

    public NoteDTO entityToDto(NoteEntity entity) {
        return NoteDTO.builder()
                .id(entity.getId())
                .dateAndTime(entity.getDateAndTime())
                .topic(entity.getTopic())
                .fullText(entity.getFullText())
                .build();
    }
}
