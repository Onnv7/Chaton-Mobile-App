package com.onnv.ChatAPI.DTO.request;


import com.fasterxml.jackson.annotation.JsonInclude;
//import com.onnv.ChatAPI.entity.Recipient;
import com.onnv.ChatAPI.entity.Room;
import com.onnv.ChatAPI.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoomInfoDto {
    private String id;
    private String userId;
    private String roomId;
    private String nickname;
    private boolean isAdmin;
}
