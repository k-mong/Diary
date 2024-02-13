package com.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {   // 클라이언트에게 받을 값

    private String email;
    private String password;
    private String name;
    private String phone;
}
