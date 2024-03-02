package com.shoppingdbapi.database.api.DTO;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReqBody {
    UUID productid;
    String productName;
    int price;
    int quantity;
    UUID sellerId;
    String productType;

}
