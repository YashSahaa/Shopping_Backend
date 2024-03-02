package com.shoppingdbapi.database.api.Controller;

import com.shoppingdbapi.database.api.DTO.Requestbody.AddOrderDto;
import com.shoppingdbapi.database.api.DTO.Requestbody.OrderProductDetailsRequestBody;
import com.shoppingdbapi.database.api.Model.OrderVsProduct;
import com.shoppingdbapi.database.api.Model.Orderdetails;
import com.shoppingdbapi.database.api.Model.PortalUser;
import com.shoppingdbapi.database.api.Repository.OrderDetailsRepo;
import com.shoppingdbapi.database.api.Repository.OrderVsProductRepo;
import com.shoppingdbapi.database.api.Repository.PortalUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db/order")
public class OrderController {
    @Autowired
    OrderDetailsRepo orderDetailsRepo;
    @Autowired
    PortalUserRepo portalUserRepo;
    @Autowired
    OrderVsProductRepo orderVsProductRepo;
    @PostMapping("/add")
    public Orderdetails addOrder(@RequestBody AddOrderDto addOrderDto){
        Orderdetails orderdetails = new Orderdetails();
        PortalUser portalUser = portalUserRepo.findById(addOrderDto.getUserId()).orElse(null);
        orderdetails.setUser(portalUser);
        orderdetails.setQuantity(addOrderDto.getQuantity());
        orderdetails.setTotalPrice(addOrderDto.getTotalPrice());
        orderDetailsRepo.save(orderdetails);
        return orderdetails;
    }
    @PostMapping("/register")
    public ResponseEntity registerProduct(@RequestBody OrderProductDetailsRequestBody orderProductDetailsRequestBody){
        OrderVsProduct orderVsProduct = new OrderVsProduct();
        orderVsProduct.setProductID(orderProductDetailsRequestBody.getProductId());
        orderVsProduct.setOrderId(orderProductDetailsRequestBody.getOrderId());
        orderVsProduct.setTotalPrice(orderProductDetailsRequestBody.getPrice());
        orderVsProduct.setTotalQuantity(orderProductDetailsRequestBody.getQuantity());
        orderVsProductRepo.save(orderVsProduct);
        return new ResponseEntity<>(orderProductDetailsRequestBody, HttpStatus.CREATED);
    }
}
