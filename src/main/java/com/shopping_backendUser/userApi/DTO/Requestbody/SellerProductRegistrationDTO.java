package com.shopping_backendUser.userApi.DTO.Requestbody;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerProductRegistrationDTO {
    String productName;
    int price;
    int quantity;
    UUID sellerId;
    String productType;
}
