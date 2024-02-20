package com.shoppingbackend.frontapi.Controller;

import com.shoppingbackend.frontapi.DTO.GeneralMessegeDTO;
import com.shoppingbackend.frontapi.DTO.RequestDTO.AclConfigDTO;
import com.shoppingbackend.frontapi.DTO.RequestDTO.RequestProductDTO;
import com.shoppingbackend.frontapi.DTO.RequestDTO.SellerProductRegistrationDTO;
import com.shoppingbackend.frontapi.DTO.UserSignUpDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/user/seller")
public class SellerUserController {
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RequestProductDTO requestProductDTO){
        UUID sellerId = requestProductDTO.getSellerId();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);

        String urlDB = "http://localhost:8081/db/user/getuser?id="+ sellerId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserSignUpDTO> resUser = restTemplate.exchange(urlDB, HttpMethod.GET,httpEntity,UserSignUpDTO.class);

        String urlAcl = "http://localhost:8081/db/acl/validate?requestor="+resUser.getBody().getType()+"&operation=ADDPRODUCT";
        ResponseEntity<AclConfigDTO> resAcl = restTemplate.exchange(urlAcl,HttpMethod.GET,httpEntity, AclConfigDTO.class);
        if(!resAcl.getBody().isValidConfig()){
            return new ResponseEntity<>(new GeneralMessegeDTO(String.format("User with id %s does not have access of this operation.",sellerId)),HttpStatus.UNAUTHORIZED);
        }
        String urlUser = "http://localhost:8083/user/seller/product/register";
        HttpEntity httpEntity1 = new HttpEntity<>(requestProductDTO,httpHeaders);
        ResponseEntity<SellerProductRegistrationDTO> ressel = restTemplate.exchange(urlUser,HttpMethod.POST,httpEntity1, SellerProductRegistrationDTO.class);
        return ressel;
    }
}
