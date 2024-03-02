package com.shoppingdbapi.database.api.Repository;

import com.shoppingdbapi.database.api.Model.OrderVsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderVsProductRepo extends JpaRepository<OrderVsProduct, UUID> {
}
