package com.diary.service;

import com.diary.domain.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final OpenApiService openApiService;

    public Board createDiary(String area) {
        // 1. 날씨정보 갖고오기
        // 1-1 이미 있는지 확인
        // 1-2 없을경우, 저장 후 가져오기

        // 다이어리 저장
        return null;
    }
}
