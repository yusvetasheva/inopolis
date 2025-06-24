package com.example.inopolis.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_flux")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentEntity {
    @Id
    @Column("id")
    Integer id;
    @Column("fio")
    String fio;
    @Column("email")
    String email;
    @Column("courses_list")
    List<String> courses = new ArrayList<>();
}
