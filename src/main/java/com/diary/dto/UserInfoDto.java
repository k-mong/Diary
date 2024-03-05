package com.diary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {   // 클라이언트에게 받을 값

    private String email;
    private String password;
    private String name;
    private String phone;


}
