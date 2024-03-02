package com.shoppingdbapi.database.api.Controller;

import com.shoppingdbapi.database.api.DTO.ProductReqBody;
import com.shoppingdbapi.database.api.Model.PortalUser;
import com.shoppingdbapi.database.api.Model.Product;
import com.shoppingdbapi.database.api.Repository.PortalUserRepo;
import com.shoppingdbapi.database.api.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/db/product")
public class ProductController {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    PortalUserRepo portalUserRepo;
    @PostMapping("/add")
    public ProductReqBody addProduct(@RequestBody ProductReqBody productRb) {
        UUID id = UUID.randomUUID();
        Product product = new Product();
        product.setId(id);
        product.setProductName(productRb.getProductName());
        product.setQuantity(productRb.getQuantity());
        product.setPrice(productRb.getPrice());
        product.setRating(0.0);
        product.setProductType(productRb.getProductType());
        PortalUser seller = portalUserRepo.findById(productRb.getSellerId()).orElse(null);
        product.setSeller(seller);
        productRepo.save(product);
        productRb.setProductid(id);
        return productRb;
    }
    @GetMapping("/get")
    public Product getProduct(@RequestParam UUID productId){
        return productRepo.findById(productId).orElse(null);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestParam UUID productId,@RequestParam int quantity){
        Product product = productRepo.findById(productId).get();
        product.setQuantity(quantity);
        productRepo.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
