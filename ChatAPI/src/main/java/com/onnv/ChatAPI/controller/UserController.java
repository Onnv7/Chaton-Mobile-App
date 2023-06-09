package com.onnv.ChatAPI.controller;

import com.onnv.ChatAPI.DTO.request.LoginUserDto;
import com.onnv.ChatAPI.DTO.request.RegisterUserDto;
import com.onnv.ChatAPI.DTO.request.UserInfoDto;
import com.onnv.ChatAPI.entity.User;
import com.onnv.ChatAPI.response.ApiResponse;
import com.onnv.ChatAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerAccount(@RequestBody RegisterUserDto user) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashPassword = encoder.encode(user.getPassword());
            user.setPassword(hashPassword);

            UserInfoDto result = userService.registerAccount(user);

            ApiResponse response = ApiResponse.builder()
                    .success(result != null)
                    .message(result != null ? "Register successfully" : "Register failed")
                    .result(result)
                    .build();

            return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginUserDto user) {
        try {
            Object existedUser = userService.login(user);
            ApiResponse response = new ApiResponse();
            if(existedUser instanceof UserInfoDto)
                response = new ApiResponse(false, "Login success", existedUser);
            else if (existedUser instanceof String){
                response = new ApiResponse(false, existedUser.toString(), null);
            }
            return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable ("id") String id) {
        try {
            UserInfoDto user = userService.findUserInformationById(id);
            return new ResponseEntity<>(
                    ApiResponse.builder()
                    .success(true)
                    .message(user != null ? "Find successfully" : "User not found")
                    .result(user)
                    .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


}
