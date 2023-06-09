package com.onnv.ChatAPI.DTO.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class LoginUserDto {
    private String username;
    private String password;
}
