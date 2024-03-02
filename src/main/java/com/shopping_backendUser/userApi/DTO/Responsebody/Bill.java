package com.shopping_backendUser.userApi.DTO.Responsebody;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Bill {
    UUID orderId;
    List<BillProductDto> products ;
    int totalQuantity;
    int totalPrice;
}
