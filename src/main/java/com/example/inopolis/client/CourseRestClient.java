package com.example.inopolis.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class CourseRestClient {

    @Bean
    public RestClient restClient(){
        return RestClient.builder()
                .baseUrl("http://localhost:8081/api/course")
                .build();

    }

}
