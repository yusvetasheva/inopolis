package com.example.inopolis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class InopolisApplication {

    public static void main(String[] args) {
        SpringApplication.run(InopolisApplication.class, args);
    }

}
