package com.onnv.ChatAPI.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onnv.ChatAPI.DTO.request.LoginUserDto;
import com.onnv.ChatAPI.DTO.request.RegisterUserDto;
import com.onnv.ChatAPI.DTO.request.UserInfoDto;
import com.onnv.ChatAPI.entity.User;
import com.onnv.ChatAPI.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfoDto registerAccount(RegisterUserDto user) {
        User userInfo = User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .birthDate(user.getBirthDate())
                .name(user.getName())
                .gender(user.getGender())
                .build();
        User newUser = userRepository.save(userInfo);
        UserInfoDto userDto = modelMapper.map(userInfo, UserInfoDto.class);
        if(newUser != null) {
            userDto.setPassword(null);
            userDto.setId(newUser.getId());
            return userDto;
        } else {
            return null;
        }
    }

    @Override
    public Object login(LoginUserDto loginInfo) {
        User existedUser = userRepository.findByEmail(loginInfo.getUsername());
        if(existedUser == null) {
            return "User not found";
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean match = encoder.matches(loginInfo.getPassword(), ((User) existedUser).getPassword());
            if(match) {
                UserInfoDto user = modelMapper.map(existedUser, UserInfoDto.class);
                return user;
            } else {
                return "Password is wrong";
            }
        }
    }

    @Override
    public UserInfoDto findUserInformationById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(null);
            UserInfoDto userInfo = modelMapper.map(user, UserInfoDto.class);
            return userInfo;
        }
        return null;
    }


}
