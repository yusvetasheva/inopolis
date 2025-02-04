package com.example.inopolis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CatFactDTO {
    @JsonProperty(value = "fact")
    String fact;
    @JsonProperty(value = "length")
    Integer length;
}
