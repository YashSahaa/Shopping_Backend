package com.shopping_backendUser.userApi.Service;

import com.shopping_backendUser.userApi.DTO.Requestbody.AddOrderRequestBody;
import com.shopping_backendUser.userApi.DTO.Requestbody.AddOrderVsProductReqBody;
import com.shopping_backendUser.userApi.DTO.Responsebody.Bill;
import com.shopping_backendUser.userApi.DTO.Responsebody.BillProductDto;
import com.shopping_backendUser.userApi.DTO.Responsebody.OrderDetailsResBody;
import com.shopping_backendUser.userApi.DTO.Responsebody.ProductResponseBody;
import com.shopping_backendUser.userApi.DTO.Requestbody.OrderDto;
import com.shopping_backendUser.userApi.DTO.Requestbody.OrderProductDto;
import com.shopping_backendUser.userApi.DTO.Utils.ApiUrlUtil;
import com.shopping_backendUser.userApi.DTO.Utils.RequestorUtil;
import com.shopping_backendUser.userApi.DTO.Utils.ShoppingLogger;
import com.shopping_backendUser.userApi.Exception.UnavailableProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {
    @Autowired
    RequestorUtil requestorUtil;
    public Bill OrderProduct(OrderDto orderDto){
        ShoppingLogger.logger.info("Inside order product in buyer service layer");
        List<OrderProductDto> orderProducts = orderDto.getOrderProductDtoList();
        List<ProductResponseBody> products = new ArrayList<>();
        int totalPrice = 0;
        int totalQuantity = 0;
        for(OrderProductDto orderProductDto : orderProducts){
            UUID productId = orderProductDto.getProductId();
            String url = ApiUrlUtil.DbApiUrl +"/product/get?productId="+ productId.toString();
            RestTemplate restTemplate = requestorUtil.getRestTemplate();
            HttpHeaders httpHeaders = requestorUtil.getHttpHeaders();
            HttpEntity httpEntity = requestorUtil.createHttpEntity(httpHeaders);
            ResponseEntity<ProductResponseBody> productres = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ProductResponseBody.class);
            ProductResponseBody pbody = productres.getBody();
            if(pbody.getQuantity()<orderProductDto.getQuantity()){
                throw new UnavailableProduct(String.format("Product %s is less in quantity having %d pieces left",pbody.getProductName(),pbody.getQuantity()));
            }
            totalPrice+=pbody.getPrice()*pbody.getQuantity();
            totalQuantity+=orderProductDto.getQuantity();
            products.add(productres.getBody());
        }
        ShoppingLogger.logger.info("got all the product present in db");

        String orderUrl = ApiUrlUtil.DbApiUrl+"/order/add";
        HttpHeaders orderHeaders = requestorUtil.getHttpHeaders();
        AddOrderRequestBody addOrderRequestBody = new AddOrderRequestBody(orderDto.getUserId(),totalQuantity,totalPrice);
        HttpEntity orderEntity = requestorUtil.createHttpEntity(addOrderRequestBody,orderHeaders);
        RestTemplate orderRequestor = requestorUtil.getRestTemplate();
        ResponseEntity<OrderDetailsResBody > orderDRB = orderRequestor.exchange(orderUrl,HttpMethod.POST,orderEntity, OrderDetailsResBody.class);

        OrderDetailsResBody order = orderDRB.getBody();

        //List<AddOrderVsProductReqBody> opResList = new ArrayList<>();
        List<BillProductDto> billProducts = new ArrayList<>();
        for(int i=0;i<products.size();i++){
            int userQuantity = orderProducts.get(i).getQuantity();
            int dbQuantity = products.get(i).getQuantity();
            int price = userQuantity*products.get(i).getPrice();
            UUID productId = products.get(i).getId();
            UUID orderId = order.getId();
            AddOrderVsProductReqBody addOrderVsProductReqBody = new AddOrderVsProductReqBody(productId,orderId,price,userQuantity);
            String opUrl = ApiUrlUtil.DbApiUrl+"/order/register";
            HttpEntity opEntity = requestorUtil.createHttpEntity(addOrderVsProductReqBody,orderHeaders);
            RestTemplate opRequestor = requestorUtil.getRestTemplate();
            ResponseEntity<AddOrderVsProductReqBody> opRes = opRequestor.exchange(opUrl,HttpMethod.POST,opEntity,AddOrderVsProductReqBody.class);
            //opResList.add(opRes.getBody());
            BillProductDto billProductDto = new BillProductDto(productId,products.get(i).getProductName(),userQuantity,price);
            billProducts.add(billProductDto);

            int updatedQuantity = dbQuantity-userQuantity;
            String pUrl = ApiUrlUtil.DbApiUrl+"/product/update?productId="+productId.toString()+"&quantity="+updatedQuantity;
            ResponseEntity pres = opRequestor.exchange(pUrl,HttpMethod.PUT,new HttpEntity<>(orderHeaders),Object.class);
        }
        Bill bill = new Bill(order.getId(),billProducts,totalQuantity,totalPrice);
        return  bill;
    }
}
