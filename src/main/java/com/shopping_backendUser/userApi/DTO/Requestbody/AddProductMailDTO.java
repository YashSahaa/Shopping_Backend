package com.shopping_backendUser.userApi.DTO.Requestbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductMailDTO {
    String mailId;
    String mailMessage;
    String subjectLine;
    String userName;
}
