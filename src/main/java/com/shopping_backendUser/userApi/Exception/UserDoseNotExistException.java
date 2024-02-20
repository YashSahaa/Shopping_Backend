package com.shopping_backendUser.userApi.Exception;

public class UserDoseNotExistException extends RuntimeException{

    public UserDoseNotExistException(String message){
        super(message);
    }
}
