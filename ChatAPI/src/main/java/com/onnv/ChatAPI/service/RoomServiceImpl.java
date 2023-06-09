package com.onnv.ChatAPI.service;

import com.onnv.ChatAPI.DTO.request.CreateRoomDto;
import com.onnv.ChatAPI.DTO.request.UpdateRoomNameDto;
import com.onnv.ChatAPI.entity.Room;
import com.onnv.ChatAPI.entity.User;
import com.onnv.ChatAPI.entity.UserRoom;
import com.onnv.ChatAPI.repository.RoomRepository;
import com.onnv.ChatAPI.repository.UserRepository;
import com.onnv.ChatAPI.repository.UserRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoomRepository userRoomRepository;

    @Override
    public Room createNewRoom(CreateRoomDto body) {
        String id = body.getHostId();
        String roomName = body.getRoomName();
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            Room newRoom = new Room();
            newRoom.setRoomName(roomName);
            roomRepository.save(newRoom);

            UserRoom newUserRoom = new UserRoom().builder()
                    .user(user)
                    .nickname(user.getName())
                    .isAdmin(true)
                    .room(newRoom)
                    .build();
            userRoomRepository.save(newUserRoom);

            return newRoom;
        }
        return null;

    }

    @Override
    public boolean updateRoomName(UpdateRoomNameDto roomInfo) {
        String roomId = roomInfo.getRoomId();
        String roomName = roomInfo.getRoomName();
        Room roomUpdate = roomRepository.findById(roomId).orElse(null);
        if(roomUpdate != null) {
            roomUpdate.setRoomName(roomName);
            roomRepository.save(roomUpdate);
            return true;
        }
        return false;
    }

    @Override
    public Room findRoomById(String id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public List<Object> getFewMessage(String roomId, int skip, int size) {
        int startPosition = (skip - 1) * size;
        List<Object> rs = roomRepository.get(roomId, startPosition, size);
        System.out.println(rs.toString() + rs.size());
        // Gọi repository để lấy danh sách tin nhắn từ phòng với phân trang
//        return messageRepository.findByRoomId(roomId, PageRequest.of(startPosition, size));
        return rs;
    }
}
