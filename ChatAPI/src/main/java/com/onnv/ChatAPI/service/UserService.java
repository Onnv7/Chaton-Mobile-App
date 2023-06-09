package com.onnv.ChatAPI.service;

import com.onnv.ChatAPI.DTO.request.LoginUserDto;
import com.onnv.ChatAPI.DTO.request.RegisterUserDto;
import com.onnv.ChatAPI.DTO.request.UserInfoDto;
import com.onnv.ChatAPI.entity.User;

public interface UserService {
    UserInfoDto registerAccount(RegisterUserDto user);
    Object login(LoginUserDto user);
    UserInfoDto findUserInformationById(String id);
}
