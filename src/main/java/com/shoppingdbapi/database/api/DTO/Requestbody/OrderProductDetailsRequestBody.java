package com.shoppingdbapi.database.api.DTO.Requestbody;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderProductDetailsRequestBody {
    UUID productId;
    UUID orderId;
    int price;
    int quantity;
}
