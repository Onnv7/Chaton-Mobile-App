package com.onnv.ChatAPI.DTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class UpdateRoomNameDto {
    private String roomId;
    private String roomName;
}
