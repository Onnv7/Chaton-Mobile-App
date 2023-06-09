package com.onnv.ChatAPI.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.onnv.ChatAPI.DTO.request.CreateUserRoomDto;
import com.onnv.ChatAPI.DTO.request.UpdateNameUserRoomDto;
import com.onnv.ChatAPI.DTO.request.UserInfoDto;
import com.onnv.ChatAPI.DTO.request.UserRoomInfoDto;
import com.onnv.ChatAPI.DTO.response.RoomInfoDto;
import com.onnv.ChatAPI.Utils.Constants;
import com.onnv.ChatAPI.entity.UserRoom;
import com.onnv.ChatAPI.response.ApiResponse;
import com.onnv.ChatAPI.service.UserRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user-room")
public class UserRoomController {
    @Autowired
    private UserRoomService userRoomService;

    @PostMapping("/add-a-new-user")
    public ResponseEntity<ApiResponse> createNewUserRoom(@RequestBody CreateUserRoomDto body) {
        try {
            RoomInfoDto userRoom = userRoomService.addNewMember(body);
            System.out.println("ke" + userRoom);
            ApiResponse response = ApiResponse.builder()
                    .success(userRoom != null)
                    .message(userRoom != null ? Constants.CREATED_SUCCESSFUL : Constants.CREATED_FAILURE)
                    .result(userRoom)
                    .build();
            return new ResponseEntity<> (response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PatchMapping("/change-nick-name")
    public ResponseEntity<ApiResponse> changeNickName(@RequestBody UpdateNameUserRoomDto body) {
        try {
            boolean success = userRoomService.changeName(body);
            return new ResponseEntity<> (
                    ApiResponse.builder()
                            .success(success)
                            .message(success ? Constants.UPDATED_SUCCESSFUL : Constants.UPDATED_FAILURE)
                            .result(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
