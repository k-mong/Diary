package com.diary.controller;

import com.diary.domain.entity.User;
import com.diary.dto.UserInfoResponseDto;
import com.diary.dto.UserLoginDto;
import com.diary.dto.UserInfoDto;
import com.diary.security.TokenProvider;
import com.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // final 생성자 만들어 줌
@RequestMapping("/user")
public class UserController {

    private final UserService userService;  // 의존성 주입
    private final TokenProvider tokenProvider;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserInfoDto userRequestDto) {    // HTTP응답을 나타내는 클래스 <Body의 타입>
        String result = userService.join(userRequestDto);
        return ResponseEntity.ok(result);   // 200번 코드와 resul 를 반환
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
        String result = userService.login(userLoginDto);
        String token = tokenProvider.generationToken(userLoginDto);
        return ResponseEntity.ok(result + token);
    }

    @GetMapping("/getMyInfo")
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        // 유요성 체크
        if(!tokenProvider.checkValidToken(token)) {
            throw new RuntimeException("토큰이 만료되었습니다.");
        }
        String userId = tokenProvider.getUserId(token);
        User user = userService.findUserInfo(userId).get();

        UserInfoResponseDto userInfoResponseDto = UserInfoResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .build();

        return ResponseEntity.ok(userInfoResponseDto);
    }

}
