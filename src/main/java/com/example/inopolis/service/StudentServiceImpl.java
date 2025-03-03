package com.example.inopolis.service;

import com.example.courses.dto.CourseDTO;
import com.example.courses.model.AddCommentToCourseRequest;
import com.example.inopolis.client.CourseRestClientApi;
import com.example.inopolis.mapper.StudentMapper;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.model.StudentDTO;
import com.example.inopolis.model.StudentEntity;
import com.example.inopolis.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.*;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    private final StudentRepository repository;

    private final CourseRestClientApi restClient;

    public StudentServiceImpl(StudentRepository repository, CourseRestClientApi restClient) {
        this.repository = repository;
        this.restClient = restClient;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentEntity> entityList = repository.findAll();
        if (entityList.isEmpty()) return new ArrayList<StudentDTO>();
        return entityList.stream().map(studentMapper::entityToDto).toList();
    }

    @Override
    public List<StudentDTO> getStudentsByCourse(String courseName) {
        if (courseName == null || courseName.isEmpty())
            throw new IllegalArgumentException("courseName не может быть пустым в методе getStudentsByCourse");

        List<StudentEntity> studentOnCourseList = repository.findStudentsByCourse(courseName);

        return studentOnCourseList.isEmpty() ?
                Collections.emptyList() :
                studentOnCourseList.stream().map(studentMapper::entityToDto).toList();
    }

    @Override
    public List<StudentDTO> getStudentWithSuchCoursesAmount(int amount) {
        List<StudentEntity> studentEntityList =
                repository.findStudentWithSuchCoursesAmount(amount);

        if (studentEntityList ==null || studentEntityList.isEmpty()) return Collections.emptyList();

        return studentEntityList.stream().map(studentMapper::entityToDto).toList();
    }

    @Override
    public List<StudentDTO> getStudentsWithCoursesLike(String course) {
        List<StudentEntity> studentEntityList= repository.findStudentsWithCoursesLike(course);

        if (studentEntityList==null || studentEntityList.isEmpty()) return  Collections.emptyList();
        return studentEntityList.stream().map(studentMapper::entityToDto).toList();
    }

    @Override
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        StudentEntity entity = studentMapper.dtoToEntity(studentDTO);
        repository.save(entity);
        return studentMapper.entityToDto(entity);
    }

    @Override
    public StudentDTO updateStudent(Integer id, StudentDTO newStudent) {
        if (id == null) throw new IllegalArgumentException("id в методе updateStudent не может быть null");
        Optional<StudentEntity> existEntity = repository.findById(id);
        if (existEntity.isEmpty())
            throw new NoSuchElementException("Нет студента с id = " + id);
        ;

        existEntity.get().setFio(newStudent.getFio());
        existEntity.get().setEmail(newStudent.getEmail());
        repository.save(existEntity.get());

        newStudent.setId(id);
        return newStudent;
    }

    @Override
    public StudentDTO deleteStudent(Integer id) {
        if (id == null) throw new IllegalArgumentException("id в методе updateStudent не может быть null");
        Optional<StudentEntity> existStudent = repository.findById(id);
        if (existStudent.isEmpty()) throw new NoSuchElementException("В БД нет элемента с id = " + id);
        repository.deleteById(id);
        return studentMapper.entityToDto(existStudent.get());
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
