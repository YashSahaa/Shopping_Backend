package com.shoppingbackend.frontapi.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellerProductRegistrationDTO {
    String productName;
    int price;
    int quantity;
    UUID sellerId;
    String productType;
}
