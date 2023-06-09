package com.onnv.ChatAPI.DTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateRoomDto {
    private String hostId;
    private String roomName;
}
