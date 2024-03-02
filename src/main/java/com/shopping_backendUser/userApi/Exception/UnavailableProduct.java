package com.shopping_backendUser.userApi.Exception;

public class UnavailableProduct extends RuntimeException{
    public UnavailableProduct(String message){
        super(message);
    }
}
