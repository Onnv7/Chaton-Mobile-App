package com.onnv.ChatAPI.controller;

import com.onnv.ChatAPI.DTO.request.MessageInfoDto;
import com.onnv.ChatAPI.DTO.request.SendMessageDto;
import com.onnv.ChatAPI.Utils.Constants;
import com.onnv.ChatAPI.entity.Message;
import com.onnv.ChatAPI.response.ApiResponse;
import com.onnv.ChatAPI.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/send-a-new-message")
    private ResponseEntity<ApiResponse> sendNewMessage(@RequestBody SendMessageDto messageInfo) throws ResponseStatusException {
        try {
            MessageInfoDto newMessage = messageService.sendMessage(messageInfo);
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .success(newMessage != null)
                            .message(newMessage != null ? Constants.CREATED_SUCCESSFUL : Constants.CREATED_FAILURE)
                            .result(newMessage)
                            .build(),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
