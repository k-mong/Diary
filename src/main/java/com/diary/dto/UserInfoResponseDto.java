package com.diary.dto;

import com.diary.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class UserInfoResponseDto {

    private String email;
    private String name;
    private String phone;


}
