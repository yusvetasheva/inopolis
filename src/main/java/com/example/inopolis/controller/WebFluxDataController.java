package com.example.inopolis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WebFluxDataController {

    @GetMapping("/hello")
    public Mono<String> getHello(){
        return Mono.just("Hellooo");
    }
}
