package com.onnv.ChatAPI.controller;

import com.onnv.ChatAPI.DTO.request.CreateRoomDto;
import com.onnv.ChatAPI.DTO.request.UpdateRoomNameDto;
import com.onnv.ChatAPI.DTO.response.RoomInfoDto;
import com.onnv.ChatAPI.Utils.Constants;
import com.onnv.ChatAPI.entity.Room;
import com.onnv.ChatAPI.repository.UserRepository;
import com.onnv.ChatAPI.response.ApiResponse;
import com.onnv.ChatAPI.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;



    @PostMapping("/create")
    private ResponseEntity<ApiResponse> createNewRoom(@RequestBody CreateRoomDto body) {
        try {
            Room result = roomService.createNewRoom(body);
            ApiResponse response = ApiResponse.builder()
                    .success(result != null)
                    .message(result != null ? Constants.CREATED_SUCCESSFUL : Constants.CREATED_FAILURE)
                    .result(result)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/update-room-name")
    private ResponseEntity<ApiResponse> updateRoomName(@RequestBody UpdateRoomNameDto body) {
        try {
            boolean success = roomService.updateRoomName(body);
            return new ResponseEntity<>(
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

    @GetMapping("/find/{roomId}")
    public ResponseEntity<ApiResponse> findRoomById(@PathVariable("roomId") String roomId) {
        try {
            Room room = roomService.findRoomById(roomId);
//            System.out.println(room);
            ApiResponse response = ApiResponse.builder()
                    .success(room != null)
                    .message(room != null ? Constants.FIND_SUCCESSFUL : Constants.FIND_FAILURE)
                    .result(room)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @GetMapping("/conversation/{roomId}")
    public ResponseEntity<ApiResponse> getConversation(@PathVariable("roomId") String roomId, @RequestParam int skip, @RequestParam int size) {
        try {
            List<Object> rs =  roomService.getFewMessage(roomId, skip, size);

            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .success(true)
                            .message(Constants.FIND_SUCCESSFUL)
                            .result(rs)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
