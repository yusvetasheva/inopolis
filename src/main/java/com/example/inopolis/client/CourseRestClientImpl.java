package com.example.inopolis.client;

import com.example.courses.model.dto.CourseDTO;
import com.example.courses.model.AddCommentToCourseRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CourseRestClientImpl implements CourseRestClientApi {

    private final RestClient restClient;

    public CourseRestClientImpl() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8081/api/course")
                .build();
    }

    @Override
    public CourseDTO checkCourseIsExist(String courseName) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/get-by-name")
                        .queryParam("name", courseName).build())
                .retrieve()
                .body(CourseDTO.class);
    }

    @Override
    public CourseDTO addCommentToCourse(AddCommentToCourseRequest request) {

       return restClient.post()
               .uri("/add-comment")
               .contentType(MediaType.APPLICATION_JSON)
               .body(request)
               .retrieve()
               .body(CourseDTO.class);
    }
}
