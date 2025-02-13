package com.example.inopolis.repository;

import com.example.inopolis.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    @Query("SELECT st FROM StudentEntity st WHERE st.course = :courseName")
    List<StudentEntity> findStudentsByCourse(@Param("courseName") String courseName);

}
