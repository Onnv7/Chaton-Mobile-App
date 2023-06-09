package com.onnv.ChatAPI.DTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onnv.ChatAPI.entity.User;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {
    private String id;
    private String name;
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String avatar;
    private User.Gender gender;
    private String phone;
    private Date birthDate;
}
