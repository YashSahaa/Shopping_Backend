package com.shoppingbackend.frontapi.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BillResponseDto {
    UUID orderId;
    List<BillProductDto> products ;
    int totalQuantity;
    int totalPrice;
}
