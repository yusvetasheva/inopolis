package com.example.inopolis.controller;

import com.example.inopolis.model.CatFactDTO;
import com.example.inopolis.service.CatFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cat-facts")
public class CatFactController {
    @Autowired
    CatFactService catFactService;

    @PostMapping (value = "/random")
    public CatFactDTO getFact(){
        return  catFactService.getFact();
    }

}
