package com.example.inopolis.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentEntity {
    @Id
    @Column(name = "id")
    Integer id;
    @Column(name = "fio")
    String fio;
    @Column(name = "email")
    String email;
    @OneToMany(targetEntity = CourseEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "student_id")
    private List<CourseEntity> courses= new ArrayList<>();
}
