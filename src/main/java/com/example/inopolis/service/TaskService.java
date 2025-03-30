package com.example.inopolis.service;

import com.example.inopolis.model.dto.TaskDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTasks();
    ResponseEntity<TaskDTO> addTask(TaskDTO taskDTO);
    ResponseEntity<TaskDTO> deleteTask(Integer id);
}
