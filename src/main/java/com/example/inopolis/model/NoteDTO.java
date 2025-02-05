package com.example.inopolis.model;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoteDTO {
    Integer id;
    LocalDate dateAndTime;
    String topic;
    String fullText;
}
