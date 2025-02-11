package com.example.inopolis.repository;

import com.example.inopolis.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
