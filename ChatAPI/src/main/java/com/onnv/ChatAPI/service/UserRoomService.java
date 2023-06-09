package com.onnv.ChatAPI.service;

import com.onnv.ChatAPI.DTO.request.CreateUserRoomDto;
import com.onnv.ChatAPI.DTO.request.UpdateNameUserRoomDto;
import com.onnv.ChatAPI.DTO.response.RoomInfoDto;
import com.onnv.ChatAPI.entity.Room;
import com.onnv.ChatAPI.entity.UserRoom;

public interface UserRoomService {
    RoomInfoDto addNewMember(CreateUserRoomDto body);
    boolean changeName(UpdateNameUserRoomDto body);
}
