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
public class cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    int quantity;
    int totalPrice;
    @OneToOne
    portalUser user;
}
