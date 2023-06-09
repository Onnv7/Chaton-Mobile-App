package com.onnv.ChatAPI.repository;

import com.onnv.ChatAPI.entity.Room;
import com.onnv.ChatAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    @Query(value = "SELECT message.id, message.content, message.send_at, user_room.nick_name, user.avatar, user.id\n" +
            "FROM user_room\n" +
            "JOIN message ON message.sender_id = user_room.id\n" +
            "JOIN room ON room.id = user_room.room_id\n" +
            "JOIN user ON user_room.user_id = user.id\n" +
            "WHERE user_room.room_id = :roomId\n" +
            "ORDER BY message.send_at DESC\n" +
            "LIMIT :size OFFSET :skip ", nativeQuery = true)
    List<Object> get(String roomId, int skip, int size);
}
