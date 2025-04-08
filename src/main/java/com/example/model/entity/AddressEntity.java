package com.example.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Address")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "city", nullable = false)
    String city;
    String street;
    @Column(name = "number_of_build")
    String numberOfBuild;
}
