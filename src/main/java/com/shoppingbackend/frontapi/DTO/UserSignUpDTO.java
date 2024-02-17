package com.shoppingbackend.frontapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserSignUpDTO {
    String name;
    String email;
    String password;
    Long contactno;
    String address;
    String type;
}
