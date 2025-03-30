package com.example.inopolis.controller;

import com.example.inopolis.model.dto.TaskDTO;
import com.example.inopolis.service.TaskService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/task")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {
    TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping(value = "/get-all")
    @PreAuthorize("hasAnyRole('VIEWER', 'USER', 'ADMIN')")
    public ResponseEntity<List<TaskDTO>> getAll(){
        return ResponseEntity.ok().body(service.getAllTasks());
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TaskDTO> addTask(@RequestBody @Valid TaskDTO task){
        return service.addTask(task);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Integer id){
        return service.deleteTask(id);
    }

}
