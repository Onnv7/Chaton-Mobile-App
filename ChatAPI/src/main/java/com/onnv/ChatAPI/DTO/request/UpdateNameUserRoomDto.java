package com.onnv.ChatAPI.DTO.request;

import lombok.Data;

@Data
public class UpdateNameUserRoomDto {
    private String nickname;
    private String userRoomId;
}
