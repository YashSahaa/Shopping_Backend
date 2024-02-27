package com.ShoppingBackend.Mail.Mail.Service.Controller;

import com.ShoppingBackend.Mail.Mail.Service.Utils.ShoppingLogger;
import com.ShoppingBackend.Mail.Mail.Service.DTO.RequestDTO.AddProductDTO;
import com.ShoppingBackend.Mail.Mail.Service.DTO.ResponseDTO.GeneralMessageDTO;
import com.ShoppingBackend.Mail.Mail.Service.Service.SellerMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail/seller")
public class SellerController {
    @Autowired
    SellerMailService sellerMailService;
    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody AddProductDTO addProductDTO){
        ShoppingLogger.logger.info("received in mail seller controller");
        try {
            sellerMailService.sendAddProductMail(addProductDTO);
            return new ResponseEntity(new GeneralMessageDTO(true), HttpStatus.CREATED);
        }catch(Exception e){
            ShoppingLogger.logger.info("in catch  add product block");
            return new ResponseEntity(new GeneralMessageDTO(false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
