package com.example.inopolis.controller;

import com.example.inopolis.model.dto.StoreDTO;
import com.example.inopolis.service.StoreService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(value = "/api/store")
public class TaskController {

    StoreService service;

    @PostMapping(value = "/add")
    public ResponseEntity<StoreDTO> add(@RequestBody @Valid StoreDTO storeDTO){
        return ResponseEntity.ok().body(service.addStore(storeDTO));
    }


    @GetMapping(value = "/get-all")
    public Page<StoreDTO> getAll(Pageable pageable) {
        return service.getAllStore(pageable);
    }
}
