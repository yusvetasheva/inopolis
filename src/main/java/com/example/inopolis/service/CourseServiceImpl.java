package com.example.inopolis.service;

import com.example.courses.model.AddCommentToCourseRequest;
import com.example.courses.model.dto.CourseDTO;
import com.example.inopolis.client.CourseRestClientApi;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.model.entity.StudentEntity;
import com.example.inopolis.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final StudentRepository repository;

    private final CourseRestClientApi restClient;

    public CourseServiceImpl(StudentRepository repository, CourseRestClientApi restClient) {
        this.repository = repository;
        this.restClient = restClient;
    }

    @Override
    public String addCourseToStudent(AddCourseToStudentRequest request) {
        if (request.getStudentId() == null)
            throw new IllegalArgumentException("studentId в методе addCourseToStudent не может быть null");
        if (request.getCourse() == null || request.getCourse().isEmpty())
            throw new IllegalArgumentException("course в методе addCourseToStudent не может быть null/empty");

        StudentEntity existStudent = repository.findById(request.getStudentId())
                .orElseThrow(() -> new NoSuchElementException("Нет студента с id = " + request.getStudentId()));

        //Проверяем, существует ли добавляемый курс
        CourseDTO existCourse = null;
        try {
            existCourse = restClient.checkCourseIsExist(request.getCourse());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (existCourse != null && existCourse.getIsActive()) {

            //Если студент уже записан на данный курс, выводим об этом сообщение
            //Если нет - добавляем курс студенту
            if (existStudent.getCourses().contains(request.getCourse()))
                return "Повтор";

            existStudent.getCourses().add(request.getCourse());
            repository.save(existStudent);
            return "Успех";
        } else return "Данный курс не активен";
    }

    @Override
    public CourseDTO addCommentToCourse(AddCommentToCourseRequest request) {

        return restClient.addCommentToCourse(request);
    }
}
