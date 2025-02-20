package com.example.inopolis.repository;

import com.example.inopolis.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    @Query("SELECT st FROM StudentEntity st JOIN st.courses c WHERE c = :courseName")
    List<StudentEntity> findStudentsByCourse(@Param("courseName") String courseName);

    @Query("SELECT st FROM StudentEntity st WHERE SIZE(st.courses) = :amount")
    List<StudentEntity> findStudentWithSuchCoursesAmount(@Param("amount") int amount);

    @Query("SELECT st FROM StudentEntity st JOIN st.courses c WHERE c LIKE CONCAT('%', :course, '%')")
    List<StudentEntity> findStudentsWithCoursesLike(@Param("course") String course);

}
