package com.diary.service;

import com.diary.domain.entity.User;
import com.diary.dto.UserInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest2 {

    @Autowired
    private UserService userService;

    @Test
    void 회워가입_테스트() {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail("1111");
        userInfoDto.setPassword("1234");
        userInfoDto.setName("김홍준");
        userInfoDto.setPhone("0100101010");

        User user = userService.join(userInfoDto);
    }
}