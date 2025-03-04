package com.example.inopolis.model.entity;

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
@Table(name = "student_final")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentEntity {
    @Id
    @Column(name = "id")
    Integer id;
    @Column(name = "fio")
    String fio;
    @Column(name = "email")
    String email;
    @ElementCollection
    @CollectionTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "course")
    List<String> courses = new ArrayList<>();
}
