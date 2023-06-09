package com.onnv.ChatAPI.service;

import com.onnv.ChatAPI.DTO.request.MessageInfoDto;
import com.onnv.ChatAPI.DTO.request.SendMessageDto;
import com.onnv.ChatAPI.DTO.request.SenderInfoDto;
import com.onnv.ChatAPI.DTO.request.UserInfoDto;
import com.onnv.ChatAPI.entity.Message;
import com.onnv.ChatAPI.entity.Room;
import com.onnv.ChatAPI.entity.User;
import com.onnv.ChatAPI.entity.UserRoom;
import com.onnv.ChatAPI.repository.MessageRepository;
import com.onnv.ChatAPI.repository.RoomRepository;
import com.onnv.ChatAPI.repository.UserRepository;
import com.onnv.ChatAPI.repository.UserRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRoomRepository userRoomRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public MessageInfoDto sendMessage(SendMessageDto message) {
        if(message.getContent().isEmpty()) {
            return null;
        }
        Room room = roomRepository.findById(message.getRoomId()).orElse(null);
        if (room == null) {
            return null;
        }
        UserRoom userRoom = userRoomRepository.findById(message.getUserRoomId()).orElse(null);
        if (userRoom == null) {
            return null;
        } else if(!userRoom.getRoom().getId().equals(message.getRoomId())) {
            return null;
        }


        User sender = userRoom.getUser();
        Message newMessage = Message.builder()
                        .sendAt(new Date())
                        .content(message.getContent())
                        .sender(userRoom)
                                .build();

        MessageInfoDto msg = modelMapper.map(newMessage, MessageInfoDto.class);
        msg.setSender(modelMapper.map(sender, SenderInfoDto.class));
        msg.getSender().setNickname(userRoom.getNickname());
        messageRepository.save(newMessage);
        return msg;
    }
}
