package com.onnv.ChatAPI.DTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onnv.ChatAPI.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterUserDto {
    private String name;
    private String email;
    private String password;
    private User.Gender gender;
    private String phone;
    private Date birthDate;
}
