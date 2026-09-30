package com.sandydev.order.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;

    private String userName;
    private String password; // need to encrypt
    private String address;
    private String city;
}
