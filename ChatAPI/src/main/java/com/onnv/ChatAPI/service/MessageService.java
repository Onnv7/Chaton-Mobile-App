package com.onnv.ChatAPI.service;

import com.onnv.ChatAPI.DTO.request.MessageInfoDto;
import com.onnv.ChatAPI.DTO.request.SendMessageDto;
import com.onnv.ChatAPI.entity.Message;

public interface MessageService {

    public MessageInfoDto sendMessage(SendMessageDto message);
}
