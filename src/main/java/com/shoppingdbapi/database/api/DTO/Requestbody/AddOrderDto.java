package com.shoppingdbapi.database.api.DTO.Requestbody;

import lombok.Data;

import java.util.UUID;

@Data
public class AddOrderDto {
    UUID userId;
    int quantity;
    int totalPrice;
}
