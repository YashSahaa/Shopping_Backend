package com.shoppingbackend.frontapi.Controller;

import com.shoppingbackend.frontapi.DTO.RequestDTO.OrderDto;
import com.shoppingbackend.frontapi.DTO.ResponseDTO.BillResponseDto;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class BuyerUserController {
    @PostMapping("/order")
    public ResponseEntity placeOrder(@RequestBody OrderDto orderDto){
        String url = "http://localhost:8083/user/buyer/order";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(orderDto,httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BillResponseDto> billres = restTemplate.exchange(url, HttpMethod.POST,httpEntity, BillResponseDto.class);
        return billres;
    }
}
