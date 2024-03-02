package com.shopping_backendUser.userApi.DTO.Requestbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    UUID userId;
    List<OrderProductDto> orderProductDtoList;
}
