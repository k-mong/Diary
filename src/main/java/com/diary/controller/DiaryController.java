package com.diary.controller;

import com.diary.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

    private final OpenApiService openApiService;
//    private final DiaryService diaryService;

//    @PostMapping("/create")
//    public ResponseEntity<Diary> createDiary(@RequestBody DiaryInfoDto diaryInfoDto) {
//        Diary diary = diaryService.createDiary(diaryInfoDto);
//        return ResponseEntity.ok(diary);
//    }
}
