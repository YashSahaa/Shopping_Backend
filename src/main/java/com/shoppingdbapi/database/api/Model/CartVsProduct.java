package com.shoppingdbapi.database.api.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartVsProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    UUID cartId;
    UUID productId;
}
