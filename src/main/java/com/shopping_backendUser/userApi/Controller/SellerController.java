package com.shopping_backendUser.userApi.Controller;

import com.shopping_backendUser.userApi.DTO.GeneruserDTO.GeneralMessageDTO;
import com.shopping_backendUser.userApi.DTO.Requestbody.SellerProductRegistrationDTO;

import com.shopping_backendUser.userApi.DTO.Utils.ShoppingLogger;
import com.shopping_backendUser.userApi.Exception.MailnotsendException;
import com.shopping_backendUser.userApi.Exception.UnauthorizedException;
import com.shopping_backendUser.userApi.Exception.UserDoseNotExistException;
import com.shopping_backendUser.userApi.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;
    @PostMapping("/product/register")
    public ResponseEntity registerProduct(@RequestBody SellerProductRegistrationDTO sellerProductRegistrationDTO){
        ShoppingLogger.logger.info("Request recived in seller controller");
        try {
            SellerProductRegistrationDTO resp = sellerService.register(sellerProductRegistrationDTO);
            return new ResponseEntity(resp, HttpStatus.CREATED);
        }catch (UnauthorizedException e){
            return new ResponseEntity(new GeneralMessageDTO(e.getMessage()),HttpStatus.UNAUTHORIZED);
        }catch (UserDoseNotExistException e){
            return new ResponseEntity(new GeneralMessageDTO(e.getMessage()),HttpStatus.NOT_FOUND);
        } catch (MailnotsendException e){
            return new ResponseEntity(new GeneralMessageDTO(e.getMessage()),HttpStatus.BAD_GATEWAY);
        }catch (RuntimeException e){
            return new ResponseEntity(new GeneralMessageDTO(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
