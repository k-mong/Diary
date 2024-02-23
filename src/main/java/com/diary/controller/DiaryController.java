package com.diary.controller;

import com.diary.dto.DiaryInfoDto;
import com.diary.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

    private final OpenApiService openApiService;

    @PostMapping("/create")
    public ResponseEntity<String > createDiary(DiaryInfoDto diaryInfoDto) {
        return ResponseEntity.ok(openApiService.getWeatherString("seoul"));
    }
}
