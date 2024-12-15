package model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    int id;
    String city;
    String street;
    @Column("number_of_build")
    String numberOfBuild;
}
