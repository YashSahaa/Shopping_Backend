package com.shopping_backendUser.userApi.DTO.Requestbody;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderProductDto {
    UUID productId;
    int quantity;

}
