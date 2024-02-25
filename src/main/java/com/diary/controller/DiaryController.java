package com.diary.controller;

import com.diary.domain.entity.Diary;
import com.diary.dto.DiaryInfoDto;
import com.diary.security.TokenProvider;
import com.diary.service.OpenApiService;
import com.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

    private final OpenApiService openApiService;
    private final DiaryService diaryService;
    private final TokenProvider tokenProvider;


    @PostMapping("/create")
    public ResponseEntity<Diary> createDiary(@RequestHeader(name = "X-AUTH-TOKEN") String token, @RequestBody DiaryInfoDto diaryInfoDto) {
        if(!tokenProvider.checkValidToken(token)) {
            throw new RuntimeException("토큰이 만료되었습니다.");
        }
        Diary diary = diaryService.createDiary(diaryInfoDto);
        return ResponseEntity.ok(diary);
    }
}
