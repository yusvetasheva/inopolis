package com.example.inopolis.controller;

import com.example.inopolis.model.OrderDTO;
import com.example.inopolis.service.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderServiceImpl service;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return service.createOrder(orderDTO);
    }
}
