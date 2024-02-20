package com.shoppingdbapi.database.api.DTO;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReqBody {
    String productName;
    int price;
    int quantity;
    UUID sellerId;
    String productType;

}
