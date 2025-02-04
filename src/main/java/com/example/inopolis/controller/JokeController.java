package com.example.inopolis.controller;

import com.example.inopolis.model.JokeDTO;
import com.example.inopolis.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {

    @Autowired
    private JokeService jokeService;

    @GetMapping(value = "/random")
    public JokeDTO getRandomJoke() {
        return jokeService.getRandomJoke();
    }
}
