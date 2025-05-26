package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(int page, int size) {
        int offset = page * size;
        return userRepository.findByPage(offset, size); // Необходимо было бы вручную передавать параметры
    }
}


