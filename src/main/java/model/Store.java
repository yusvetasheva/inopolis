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
public class Store {
    int id;
    Integer addressId;
    Integer capacity;
    Integer fullness;

    public Store(Integer addressId, Integer capacity, Integer fullness){
        this.addressId = addressId;
        this.capacity = capacity;
        this.fullness = fullness;
    }
}