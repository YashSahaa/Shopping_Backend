package com.shopping_backendUser.userApi.Service;

import com.shopping_backendUser.userApi.DTO.GeneruserDTO.UserDTO;
import com.shopping_backendUser.userApi.DTO.Requestbody.SellerProductRegistrationDTO;
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
        String url = "http://localhost:8081/db/user/getuser?id="+ sellerId.toString();
        ResponseEntity<UserDTO> userRes = restTemplate.exchange(url, HttpMethod.GET,httpEntity, UserDTO.class);
        if(userRes.getBody()==null){
            throw new UserDoseNotExistException(String.format("User with id %s does not exist in system. ",sellerId.toString()));
        }
        if(!userRes.getBody().getType().equals("Seller")){
            throw new UnauthorizedException(String.format("User with id %s does not have access of this operation.",sellerId.toString()));
        }
        String urlp = "http://localhost:8081/db/product/add";
        HttpEntity httpEntitypP = new HttpEntity(sellerProductRegistrationDTO,httpHeaders);
        ResponseEntity<SellerProductRegistrationDTO>  resP = restTemplate.exchange(urlp,HttpMethod.POST,httpEntitypP, SellerProductRegistrationDTO.class);
        if(resP.getBody()==null) throw new RuntimeException(String.format("Product not added !!"));
        return resP.getBody();
    }
}
