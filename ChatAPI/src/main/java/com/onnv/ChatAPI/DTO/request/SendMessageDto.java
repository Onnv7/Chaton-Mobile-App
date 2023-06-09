package com.onnv.ChatAPI.DTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendMessageDto {
    private String roomId;
    private String content;
    private String userRoomId;
}
