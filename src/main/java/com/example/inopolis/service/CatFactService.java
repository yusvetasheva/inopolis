package com.example.inopolis.service;

import com.example.inopolis.model.CatFactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class CatFactService {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public CatFactDTO getFact(){
        return restTemplate.getForObject("https://catfact.ninja/fact", CatFactDTO.class);
    }
}
