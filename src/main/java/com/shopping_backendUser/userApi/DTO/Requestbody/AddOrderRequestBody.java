package com.shopping_backendUser.userApi.DTO.Requestbody;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AddOrderRequestBody {
    UUID userId;
    int quantity;
    int totalPrice;
}
