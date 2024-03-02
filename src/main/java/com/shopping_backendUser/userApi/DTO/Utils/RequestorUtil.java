package com.shopping_backendUser.userApi.DTO.Utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestorUtil {
    public HttpHeaders getHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    public HttpEntity createHttpEntity(HttpHeaders httpHeaders){
        return new HttpEntity<>(httpHeaders);
    }
    public HttpEntity createHttpEntity(Object body ,HttpHeaders httpHeaders){
        return new HttpEntity(body,httpHeaders);
    }
}
