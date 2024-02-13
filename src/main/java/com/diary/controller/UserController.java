package com.diary.controller;

import com.diary.dto.UserRequestDto;
import com.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor    // final 생성자 만들어 줌
@RequestMapping("user")
public class UserController {

    private final UserService userService;  // 의존성 주입

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserRequestDto userRequestDto) {    // HTTP응답을 나타내는 클래스 <Body의 타입>
        String result = userService.join(userRequestDto);
        return ResponseEntity.ok(result);   // 200번 코드와 resul 를 반환
    }


}
