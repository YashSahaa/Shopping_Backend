package com.shoppingbackend.frontapi.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillProductDto {
    UUID productId;
    String productName;
    int quantity;
    int price;
}
