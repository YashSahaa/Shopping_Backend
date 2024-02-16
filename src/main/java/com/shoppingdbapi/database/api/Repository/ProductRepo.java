package com.shoppingdbapi.database.api.Repository;

import com.mongodb.client.MongoDatabase;
import com.shoppingdbapi.database.api.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepo extends MongoRepository<Product, UUID> {
}
