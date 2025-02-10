package com.example.inopolis.service;

import com.example.inopolis.model.CourseEnum;
import com.example.inopolis.model.StudentDTO;
import com.example.inopolis.model.StudentEntity;
import com.example.inopolis.repository.StudentRepository;
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
    StudentRepository repository;

    @InjectMocks
    private StudentServiceImpl service;

    @Test
    public void addStudent() {
        StudentDTO studentDTO = getDto();
        service.addStudent(studentDTO);
        verify(repository, times(1)).save(any());
    }

    @Test
    public void deleteStudent(){
        service.deleteStudent(1);
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    public void updateWithNullId(){
        StudentDTO studentDTO = getDto();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateStudent(null, studentDTO);
        });

        assertEquals("id в методе updateStudent не может быть null", exception.getMessage());
    }

    @Test
    public void updateWithUnexistedId(){
        StudentDTO studentDTO = getDto();

        when(repository.findById(999)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            service.updateStudent(999, studentDTO);
        });


        assertEquals("Нет студента с id = 999", exception.getMessage());
    }

    @Test
    public void updateSuccess(){
        StudentDTO studentDTO = getDto();
        StudentEntity studentEntity = getEntity();
        when(repository.findById(3)).thenReturn(Optional.of(studentEntity));

        service.updateStudent(3, studentDTO);

        verify(repository, times(1)).save(studentEntity);
    }

    @Test
    public void getAllNotes(){
        service.getAllStudents();
        verify(repository, times(1)).findAll();
    }


    public StudentDTO getDto(){
        return StudentDTO.builder()
                .id(3)
                .fio("Петров Петр Петрович")
                .email("petrov200@gmail.com")
                .courseEnum(CourseEnum.ENGLISH)
                .build();
    }

    public StudentEntity getEntity() {
        return StudentEntity.builder()
                .id(3)
                .fio("Петров Петр Петрович")
                .email("petrov200@gmail.com")
                .courseEnum(CourseEnum.ENGLISH)
                .build();
    }
}
