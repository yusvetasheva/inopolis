package com.example.inopolis.service;

import com.example.inopolis.model.NoteDTO;
import com.example.inopolis.model.NoteEntity;
import com.example.inopolis.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {
    @Mock
    NoteRepository repository;

    @InjectMocks
    private NoteServiceImpl service;

    @Test
    public void createNote() {
        NoteDTO noteDTO = getDto();
        service.create(noteDTO);
        verify(repository, times(1)).save(any());
    }

    @Test
    public void deleteNote(){
        service.delete(1);
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    public void updateWithNullId(){
        NoteDTO noteDTO = getDto();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.update(null, noteDTO);
        });

        assertEquals("id в методе update не может быть null", exception.getMessage());
    }

    @Test
    public void updateWithUnexistedId(){
        NoteDTO noteDTO = getDto();

        when(repository.findById(999)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            service.update(999, noteDTO);
        });


        assertEquals("Нет элемента с id = 999", exception.getMessage());
    }

    @Test
    public void updateSuccess(){
        NoteDTO noteDTO = getDto();
        NoteEntity noteEntity = getEntity();
        when(repository.findById(3)).thenReturn(Optional.of(noteEntity));

        service.update(3, noteDTO);

        verify(repository, times(1)).save(noteEntity);
    }

    @Test
    public void getByIdSuccess(){
        NoteEntity noteEntity = getEntity();
        when(repository.findById(3)).thenReturn(Optional.of(noteEntity));

        service.getById(3);

        verify(repository, times(1)).findById(3);

    }

    @Test
    public void getByIdWithNullId(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{
            service.getById(null);
        });

        assertEquals("id в методе getById не может быть null", exception.getMessage());
    }

    @Test
    public void getAllNotes(){
        service.getAllNotes();
        verify(repository, times(1)).findAll();
    }


    public NoteDTO getDto() {
        return NoteDTO.builder()
                .id(3)
                .topic("Тестовая тема")
                .fullText("Тестовый текст")
                .build();
    }

    public NoteEntity getEntity() {
        return NoteEntity.builder()
                .id(3)
                .topic("Тестовая тема")
                .fullText("Тестовый текст")
                .build();
    }
}
