package com.onnv.ChatAPI.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onnv.ChatAPI.entity.UserRoom;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RoomInfoDto {
    private String id;
    private String roomName;
    private String image;
    private String latestMsg;
    private Date createdAt;
}
