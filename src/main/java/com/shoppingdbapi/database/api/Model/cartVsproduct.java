package com.shoppingdbapi.database.api.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class cartVsproduct {
    UUID cartId;
    UUID productId;
}
