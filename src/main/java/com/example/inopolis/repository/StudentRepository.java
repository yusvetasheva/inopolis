package com.example.inopolis.repository;

import com.example.inopolis.model.entity.StudentEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<StudentEntity, Integer> {

    @Query("SELECT * FROM student_flux WHERE :courseName = ANY(courses_list)")
    Flux<StudentEntity> findStudentsByCourse(@Param("courseName") String courseName);

    @Query("SELECT * FROM student_flux WHERE array_length(courses_list, 1) = :amount")
    Flux<StudentEntity> findStudentWithSuchCoursesAmount(@Param("amount") int amount);

}
