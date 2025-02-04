package com.example.inopolis.service;

import com.example.inopolis.model.JokeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {

    @Autowired
    private RestTemplate restTemplate;

    public JokeDTO getRandomJoke() {
        String url = "https://official-joke-api.appspot.com/random_joke";
        return restTemplate.getForObject(url, JokeDTO.class);
    }
}
