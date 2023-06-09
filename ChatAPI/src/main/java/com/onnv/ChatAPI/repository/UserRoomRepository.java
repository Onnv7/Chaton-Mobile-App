package com.onnv.ChatAPI.repository;

import com.onnv.ChatAPI.entity.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoomRepository extends JpaRepository<UserRoom, String> {
    UserRoom findByUserIdAndRoomId(String userId, String roomId);
}
