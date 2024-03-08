package com.diary.service;

import com.diary.domain.entity.User;
import com.diary.domain.repository.UserRepository;
import com.diary.dto.UserInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired  // 테스트에서는 이거사용
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Test
    void 회원가입_테스트() {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail("0998");
        userInfoDto.setPassword("1234");
        userInfoDto.setName("김홍준");
        userInfoDto.setPhone("0100101010");

        User user = userService.join(userInfoDto);
        // 1. 성공을 위한 테스트(항상 마지막에 하기)
        assertEquals(user.getEmail(), userInfoDto.getEmail());
//        assertTrue(user!=null);
        assertNotNull(user);

        // 2. 실패를 위한 테스트
        UserInfoDto userInfoDto2 = new UserInfoDto();
        userInfoDto2.setEmail("5676");
        userInfoDto2.setPassword("1111");
        userInfoDto2.setName("김홍준");
        userInfoDto2.setPhone("0100101010");

        // 2-1 비밀번호 틀린걸 넣었을 때, 알아차리는지
        assertTrue(passwordEncoder.matches(user.getPassword(), userInfoDto2.getPassword()));
    }

    @Test
    void 회원리스트() {
        List<User> result = userRepository.findAll();
        System.out.println(">>>>>>>>>>>>"+result.size());
        assertTrue(result.size() == 4);

    }

    /**
     * 토큰정보 테스트 해보기
     */
    @Test
    void 토큰확인() {

    }
}