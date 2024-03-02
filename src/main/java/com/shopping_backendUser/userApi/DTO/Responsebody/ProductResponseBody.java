package com.shopping_backendUser.userApi.DTO.Responsebody;

import com.shopping_backendUser.userApi.DTO.GeneruserDTO.UserDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductResponseBody {
    UUID id;
    String productName;
    int price;
    int quantity;
    UserDTO seller;
    double rating;
    String productType;
}
