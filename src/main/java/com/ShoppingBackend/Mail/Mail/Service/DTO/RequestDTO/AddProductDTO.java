package com.ShoppingBackend.Mail.Mail.Service.DTO.RequestDTO;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddProductDTO {
    String mailId;
    String mailMessage;
    String subjectLine;
    String userName;
}
