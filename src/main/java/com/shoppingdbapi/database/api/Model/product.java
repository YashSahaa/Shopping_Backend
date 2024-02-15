package com.shoppingdbapi.database.api.Model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class product {
    @Id
    UUID id;
    String productName;
    int price;
    int qunatity;
    portalUser seller;
    double rating;
    String productType;

}
