package com.diary.dto;

import com.diary.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponseDto {

    private String email;
    private String name;
    private String phone;

    @Builder
    public UserInfoResponseDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.phone = user.getPhone();
    }
}
