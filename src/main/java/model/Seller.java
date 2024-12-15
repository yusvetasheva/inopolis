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
public class Seller {
    int id;
    String fio;
    @Column("cust_position")
    String custPosition;
    @Column("store_id")
    Integer storeId;
    Integer salary;
}