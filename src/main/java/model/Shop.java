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
public class Shop {
    int id;
    @Column("address_id")
    Integer addressId;
    @Column("store_id")
    Integer storeId;
    @Column("shop_name")
    String shopName;
}
