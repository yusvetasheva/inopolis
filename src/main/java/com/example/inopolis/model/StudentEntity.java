package com.example.inopolis.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_new")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentEntity {
    @Id
    @Column(name = "id")
    Integer id;
    @Column(name = "fio")
    String fio;
    @Column(name = "email")
    String email;
    @Column(name = "course")
    String course;
}
