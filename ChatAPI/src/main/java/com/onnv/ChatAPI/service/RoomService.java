package com.onnv.ChatAPI.service;

import com.onnv.ChatAPI.DTO.request.CreateRoomDto;
import com.onnv.ChatAPI.DTO.request.UpdateRoomNameDto;
import com.onnv.ChatAPI.entity.Room;

import java.util.List;

public interface RoomService {
    Room createNewRoom(CreateRoomDto body);
    boolean updateRoomName(UpdateRoomNameDto roomInfo);
    Room findRoomById(String id);

    List<Object> getFewMessage(String roomId, int skip, int size);
}
