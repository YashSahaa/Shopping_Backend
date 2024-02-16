package com.shoppingdbapi.database.api.Controller;

import com.shoppingdbapi.database.api.DTO.ProductReqBody;
import com.shoppingdbapi.database.api.Model.PortalUser;
import com.shoppingdbapi.database.api.Model.Product;
import com.shoppingdbapi.database.api.Repository.PortalUserRepo;
import com.shoppingdbapi.database.api.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/db/product")
public class ProductController {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    PortalUserRepo portalUserRepo;
    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductReqBody productRb) {
        UUID id = UUID.randomUUID();
        Product product = new Product();
        product.setId(id);
        product.setProductName(productRb.getProductName());
        product.setQunatity(productRb.getQunatity());
        product.setPrice(productRb.getPrice());
        product.setRating(0.0);
        product.setProductType(productRb.getProductType());
        PortalUser seller = portalUserRepo.findById(productRb.getSellerId()).orElse(null);
        product.setSeller(seller);
        productRepo.save(product);
        return product;
    }
}
