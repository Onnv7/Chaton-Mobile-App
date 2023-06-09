package com.onnv.ChatAPI.service;

import com.onnv.ChatAPI.DTO.request.CreateUserRoomDto;
import com.onnv.ChatAPI.DTO.request.UpdateNameUserRoomDto;
import com.onnv.ChatAPI.DTO.response.RoomInfoDto;
import com.onnv.ChatAPI.entity.Room;
import com.onnv.ChatAPI.entity.User;
import com.onnv.ChatAPI.entity.UserRoom;
import com.onnv.ChatAPI.repository.RoomRepository;
import com.onnv.ChatAPI.repository.UserRepository;
import com.onnv.ChatAPI.repository.UserRoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserRoomServiceImpl implements UserRoomService{
    @Autowired
    private UserRoomRepository userRoomRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public RoomInfoDto addNewMember(CreateUserRoomDto body) {
        String memberId = body.getUserId();
        String roomId = body.getRoomId();

        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            return null;
        }

        UserRoom userRoom = userRoomRepository.findByUserIdAndRoomId(memberId, roomId);
        if (userRoom != null) {
            return null;
        }

        User user = userRepository.findById(memberId).orElse(null);
        if(user == null) {
            return null;
        }

        UserRoom newUserRoom = UserRoom.builder()
                        .room(room)
                        .user(user)
                        .nickname(user.getName())
                        .build();
        entityManager.persist(newUserRoom);
        System.out.println(modelMapper.map(room, RoomInfoDto.class));
        return modelMapper.map(room, RoomInfoDto.class);
    }

    @Override
    public boolean changeName(UpdateNameUserRoomDto body) {
        String nickname = body.getNickname();
        String userRoomId = body.getUserRoomId();

        UserRoom userRoom = userRoomRepository.findById(userRoomId).orElse(null);
        if(userRoom == null) {
            return false;
        }
        userRoom.setNickname(nickname);
        userRoomRepository.save(userRoom);
        return true;
    }
}
