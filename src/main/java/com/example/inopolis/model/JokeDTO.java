package com.example.inopolis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**Данный класс нужен для передачи java-классов между клиентом и контроллером*/
@Getter
@Setter
@NoArgsConstructor
public class JokeDTO {
    @JsonProperty("type")
    String type;
    @JsonProperty("setup")
    String setup;
    @JsonProperty("punchline")
    String punchline;
    @JsonProperty("id")
    Integer id;

}
