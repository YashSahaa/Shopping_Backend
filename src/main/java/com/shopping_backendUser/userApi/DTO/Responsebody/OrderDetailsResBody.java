package com.shopping_backendUser.userApi.DTO.Responsebody;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetailsResBody {
    UUID id;
    int quantity;
    double totalPrice;
    boolean isDelivered;
}
