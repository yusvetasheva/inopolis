package com.example.inopolis.repository;

import com.example.inopolis.model.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

}
