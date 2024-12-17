package model;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String numberOfBuild;

    public Address (String city, String street, String numberOfBuild){
        this.city =city;
        this.street = street;
        this.numberOfBuild = numberOfBuild;
    }
}
