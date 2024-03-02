package com.shopping_backendUser.userApi.Controller;

import com.shopping_backendUser.userApi.DTO.Requestbody.OrderDto;
import com.shopping_backendUser.userApi.DTO.Responsebody.Bill;
import com.shopping_backendUser.userApi.DTO.Utils.ShoppingLogger;
import com.shopping_backendUser.userApi.Service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/buyer")
public class BuyerController {

    @Autowired
    BuyerService buyerService;
    @PostMapping("/order")
    public ResponseEntity placeOrder(@RequestBody OrderDto orderDto){
        ShoppingLogger.logger.info("Request received in buyer contoller "+orderDto.toString());
        Bill bill = buyerService.OrderProduct(orderDto);
        return new ResponseEntity(bill, HttpStatus.OK);
    }
}
