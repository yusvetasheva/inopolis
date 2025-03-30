package com.example.inopolis.service;

import com.example.inopolis.mapper.TaskMapper;
import com.example.inopolis.model.dto.TaskDTO;
import com.example.inopolis.model.entity.TaskEntity;
import com.example.inopolis.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService {

    TaskRepository repository;
    TaskMapper mapper = TaskMapper.INSTANCE;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<TaskEntity> taskList = repository.findAll();
        if (taskList.isEmpty()) return Collections.emptyList();
        return taskList.stream().map(mapper::entityToDto).toList();
    }

    @Override
    public ResponseEntity<TaskDTO> addTask(TaskDTO taskDTO) {
        if (taskDTO.getCreatedDate() == null) taskDTO.setCreatedDate(LocalDate.now());

        repository.save(mapper.dtoToEntity(taskDTO));
        return ResponseEntity.ok().body(taskDTO);
    }

    @Override
    public ResponseEntity<TaskDTO> deleteTask(Integer id) {
        Optional<TaskEntity> exist = repository.findById(id);
        if (exist.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().body(mapper.entityToDto(exist.get()));
        }

        throw new NoSuchElementException("Нет таски с id  = " + id);
    }
}
