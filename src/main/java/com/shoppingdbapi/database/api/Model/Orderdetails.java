package com.shoppingdbapi.database.api.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Orderdetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    int quantity;
    double totalPrice;
    boolean isDelivered;
    @ManyToOne
    PortalUser user;
}
