package com.onnv.ChatAPI.DTO.request;

import lombok.Data;

import java.util.Date;

@Data
public class MessageInfoDto {
    private SenderInfoDto sender;
    private String content;
    private Date sendAt;
}
