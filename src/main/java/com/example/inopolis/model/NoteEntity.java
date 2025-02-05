package com.example.inopolis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "note")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoteEntity {
    @Id
    @Column(name = "id")
    Integer id;
    @Column(name = "date_and_time")
    LocalDate dateAndTime;
    @Column(name = "topic")
    String topic;
    @Column(name = "full_text")
    String fullText;
}
