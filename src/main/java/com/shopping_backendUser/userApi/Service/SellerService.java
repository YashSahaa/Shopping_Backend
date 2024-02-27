package com.shopping_backendUser.userApi.Service;

import com.shopping_backendUser.userApi.DTO.GeneruserDTO.ResponseDTO.MailResDto;
import com.shopping_backendUser.userApi.DTO.GeneruserDTO.UserDTO;
import com.shopping_backendUser.userApi.DTO.Requestbody.AddProductMailres;
import com.shopping_backendUser.userApi.DTO.Requestbody.SellerProductRegistrationDTO;
import com.shopping_backendUser.userApi.DTO.Utils.ApiUrlUtil;
import com.shopping_backendUser.userApi.DTO.Utils.ShoppingLogger;
import com.shopping_backendUser.userApi.Exception.MailnotsendException;
import com.shopping_backendUser.userApi.Exception.UnauthorizedException;
import com.shopping_backendUser.userApi.Exception.UserDoseNotExistException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class SellerService {

    public SellerProductRegistrationDTO register(SellerProductRegistrationDTO sellerProductRegistrationDTO){
        UUID sellerId = sellerProductRegistrationDTO.getSellerId();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ShoppingLogger.logger.info("calling db api,user");
        String url = "http://localhost:8081/db/user/getuser?id="+ sellerId.toString();
        ResponseEntity<UserDTO> userRes = restTemplate.exchange(url, HttpMethod.GET,httpEntity, UserDTO.class);
        if(userRes.getBody()==null){
            throw new UserDoseNotExistException(String.format("User with id %s does not exist in system. ",sellerId.toString()));
        }
        if(!userRes.getBody().getType().equals("Seller")){
            throw new UnauthorizedException(String.format("User with id %s does not have access of this operation.",sellerId.toString()));
        }
        ShoppingLogger.logger.info("calling db api,product");
        String urlp = "http://localhost:8081/db/product/add";
        HttpEntity httpEntitypP = new HttpEntity(sellerProductRegistrationDTO,httpHeaders);
        ResponseEntity<SellerProductRegistrationDTO>  resP = restTemplate.exchange(urlp,HttpMethod.POST,httpEntitypP, SellerProductRegistrationDTO.class);
        if(resP.getBody()==null) throw new RuntimeException(String.format("Product not added !!"));

        ShoppingLogger.logger.info("Calling mail api ,add product");
        String mailurl = ApiUrlUtil.mailurl+"/seller/addProduct";
        String mail = generateMailForAddProduct(userRes.getBody().getName(),sellerProductRegistrationDTO.getProductName(),sellerProductRegistrationDTO.getPrice(),sellerProductRegistrationDTO.getQuantity(),sellerProductRegistrationDTO.getProductType());
        AddProductMailres addProductMailres = new AddProductMailres(userRes.getBody().getEmail(),mail,"Congratulations !! Product got added",userRes.getBody().getName());
        HttpEntity mailentity = new HttpEntity(addProductMailres,httpHeaders);
        ResponseEntity<MailResDto> mailres = restTemplate.exchange(mailurl,HttpMethod.POST,mailentity,MailResDto.class);
        ShoppingLogger.logger.info("Mail Response : " + mailres.getBody().toString());
        if(!mailres.getBody().isSuccess()) throw new MailnotsendException(String.format("Mail not send to userId %s",userRes.getBody().getId()));

        ShoppingLogger.logger.info("call ended successfully");
        return resP.getBody();
    }
    public String generateMailForAddProduct(String sellername,String productname,int price,int quantity,String type){
        String mail = String.format("Hello %s\n"+
                "Your product got successfully added to our shopping website.\nBelow are details of your product.\n"+
                " 1. Product name : %s\n"+
                " 2. Price : %d\n"+
                " 3. Quantity : %d\n"+
                " 4. Product Type : %s\n",sellername,productname,price,quantity,type);
        return mail;
    }
}
