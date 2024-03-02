package com.shoppingdbapi.database.api.Repository;

import com.shoppingdbapi.database.api.Model.Orderdetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDetailsRepo extends JpaRepository<Orderdetails, UUID> {

}
