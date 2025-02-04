package com.example.inopolis.service;

import com.example.inopolis.model.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<String> createOrder(OrderDTO order);
}
