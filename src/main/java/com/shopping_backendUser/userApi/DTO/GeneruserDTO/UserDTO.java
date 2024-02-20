package com.shopping_backendUser.userApi.DTO.GeneruserDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class UserDTO {
    UUID id;
    String name;
    String email;
    String password;
    Long contactno;
    String address;
    String type;
}
