package com.diary.service;

import com.diary.domain.entity.Diary;
import com.diary.domain.entity.Weather;
import com.diary.dto.DiaryInfoDto;
import com.diary.dto.UserLoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DiaryServiceTest {

    @Autowired
    private DiaryService diaryService;
    @Autowired
    private UserService userService;

    @Test
    void 날씨정보_테스트() {
        Weather weather = diaryService.getWeatherInfo("seoul");
        assertNotNull(weather);

    }

    @Test
    void 다이어리등록_테스트() {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("1111");
        userLoginDto.setPassword("1111");
        userService.login(userLoginDto);


        DiaryInfoDto diaryInfoDto = new DiaryInfoDto();
        diaryInfoDto.setTitle("제목");
        diaryInfoDto.setContent("내용");
        diaryInfoDto.setArea("seoul");

        Diary diary = diaryService.createDiary(diaryInfoDto, "1111");

        assertTrue(diary != null);
//        assertEquals(); 두개의 값 비교하는 메서드
    }
}